package com.opipo.rev.lorewiki.service.impl;

import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl extends AbstractServiceDTO<Page, String> implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    protected MongoRepository<Page, String> getRepository() {
        return pageRepository;
    }

    @Override
    protected Page buildElement(String id) {
        Page page = new Page();
        page.setUrl(id);
        return page;
    }

    @Override
    public Page save(Page element) {
        if (checkTerms(element)) {
            throw new IllegalArgumentException("The term exists");
        }
        return super.save(element);
    }

    private Boolean checkTerms(Page element) {
        return element.getTerms() != null && element.getTerms().stream().anyMatch(p -> pageRepository.findByTerms(p).stream().filter(pr -> !element.getUrl().equalsIgnoreCase(pr.getUrl())).anyMatch(pr -> pr.getTerms().contains(p)));
    }

}
