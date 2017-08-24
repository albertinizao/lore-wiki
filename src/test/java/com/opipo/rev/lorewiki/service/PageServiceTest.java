package com.opipo.rev.lorewiki.service;

import com.opipo.rev.lorewiki.MockitoExtension;
import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.impl.AbstractServiceDTO;
import com.opipo.rev.lorewiki.service.impl.PageServiceImpl;
import com.opipo.rev.lorewiki.service.interfaces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PageServiceTest implements CRUDServiceTest<Page, String> {

    @Mock
    PageRepository pageRepository;
    @InjectMocks
    PageServiceImpl pageService;

    @Override
    public String getCorrectID() {
        return "url";
    }

    @Override
    public String getIncorrectID() {
        return "FAKEURL";
    }

    @Override
    public MongoRepository<Page, String> getRepository() {
        Assertions.assertNotNull(pageRepository);
        return pageRepository;
    }

    @Override
    public AbstractServiceDTO<Page, String> getService() {
        Assertions.assertNotNull(pageService);
        return pageService;
    }

    @Override
    public Page buildElement(String id) {
        Page page = new Page();
        page.setUrl(id);
        page.setTerms(new ArrayList<>());
        return page;
    }

    @Test
    void givenElementWithUniqueTerm(){
        Page page = buildElement("idTerm");
        String term = "terCorrect";
        page.setTerms(term);
        Mockito.when(pageRepository.findByTerms(term)).thenReturn(Arrays.asList(page));
        Mockito.when(getRepository().save(page)).thenReturn(page);
        Page actual = getService().save(page);
        assertEquals(page, actual);
    }

    @Test
    void givenElementWithDuplicatedTerm(){
        Page page = buildElement("idTerm");
        String term = "terCorrect";
        page.setTerms(term);
        Page page2 = buildElement("idTerm2");
        page2.setTerms(term);
        Mockito.when(pageRepository.findByTerms(term)).thenReturn(Arrays.asList(page,page2));
        Mockito.when(getRepository().save(page)).thenReturn(page);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->getService().save(page));
        assertEquals("The term exists",iae.getLocalizedMessage());
    }
}
