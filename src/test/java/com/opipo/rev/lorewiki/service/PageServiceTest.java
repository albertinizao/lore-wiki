package com.opipo.rev.lorewiki.service;

import com.opipo.rev.lorewiki.MockitoExtension;
import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.impl.AbstractServiceDTO;
import com.opipo.rev.lorewiki.service.impl.PageServiceImpl;
import com.opipo.rev.lorewiki.service.interfaces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.repository.MongoRepository;

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
        return page;
    }
}
