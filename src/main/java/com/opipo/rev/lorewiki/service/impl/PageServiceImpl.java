package com.opipo.rev.lorewiki.service.impl;

import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl  extends AbstractServiceDTO<Page, String> implements PageService {

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

}
