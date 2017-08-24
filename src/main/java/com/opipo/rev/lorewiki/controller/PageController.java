package com.opipo.rev.lorewiki.controller;

import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.service.PageService;
import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/page")
@Api(value = "REST API to manage the pages of the wiki")
public class PageController extends AbstractCRUDController<Page,String>{

    @Autowired
    private PageService pageService;

    @Override
    protected ServiceDTOInterface<Page, String> getService() {
        return pageService;
    }

    @Override
    protected Function<Page, String> getIdFromElement() {
        return p->p.getUrl();
    }
}
