package com.opipo.rev.lorewiki.service;

import com.opipo.rev.lorewiki.MockitoExtension;
import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.impl.TermServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class TermServiceTest {

    @Mock
    private PageRepository pageRepository;
    @InjectMocks
    private TermServiceImpl termService;

    @Test
    void givenTermFromNoPageThenReturnNull() {
        String term = "term";
        assertNull(termService.getUrlFromTerm(term));
    }

    @Test
    void givenTermFromZeroPagesThenReturnNull() {
        String term = "term";
        Mockito.when(pageRepository.findByTerms(term)).thenReturn(new ArrayList<>());
        assertNull(termService.getUrlFromTerm(term));
    }

    @Test
    void givenTermFromPageThenReturnTheUrl() {
        String term = "term";
        String url = "url";
        Page page = new Page();
        page.setUrl(url);
        page.setTerms(term);
        Mockito.when(pageRepository.findByTerms(term)).thenReturn(Arrays.asList(page));
        assertEquals(url, termService.getUrlFromTerm(term));
    }

    @Test
    void givenTermFromMultiplePageThenReturnTheMultipleUrl() {
        String term = "term";
        String url = "url";
        Page page = new Page();
        page.setUrl(url);
        page.setTerms(term);
        Page page2 = new Page();
        page2.setUrl(url + "2");
        page2.setTerms(term);
        Mockito.when(pageRepository.findByTerms(term)).thenReturn(Arrays.asList(page, page2));
        assertEquals("multiple?urls=" + url + "&" + url + "2", termService.getUrlFromTerm(term));
    }

    @Test
    void givenComplexTextAndTermsThenChangeAll() {
        String text = "Existen dos posibilidades: estamos solos en el universo o no lo estamos. Ambas igualmente aterradoras.";
        String expected = "[url=2]Existen[/url] dos posibilidades: [url=1]estamos[/url] solos en el universo [url=3]o[/url] no lo [url=1]estamos[/url]. Ambas igualmente aterradoras.";
        List<Page> pages = new ArrayList<>();
        Integer i = 1;
        pages.add(buildPage((i++).toString(), "estamos"));
        pages.add(buildPage((i++).toString(), "existen"));
        pages.add(buildPage((i++).toString(), "o"));
        Mockito.when(pageRepository.findAll()).thenReturn(pages);
        for (i = 0; i < pages.size(); i++) {
            Mockito.when(pageRepository.findByTerms(pages.get(i).getTerms().get(0))).thenReturn(Arrays.asList(pages.get(i)));
        }
        String result = termService.addLinksToPage("prueba.html", text);
        assertEquals(expected,result);
    }

    private Page buildPage(String id, String term) {
        Page page = new Page();
        page.setUrl(id);
        page.setTerms(term);
        return page;
    }
}
