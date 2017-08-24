package com.opipo.rev.lorewiki.controller;

import com.opipo.rev.lorewiki.MockitoExtension;
import com.opipo.rev.lorewiki.controller.interfaces.CRUDControllerTest;
import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.service.PageService;
import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class PageControllerTest implements CRUDControllerTest<Page, String> {

    @InjectMocks
    private PageController pageController;

    @Mock
    private PageService pageService;

    @Override
    public boolean checkIdFromElement(String id, Page element) {
        return element.getUrl().equals(id);
    }

    @Override
    public AbstractCRUDController<Page, String> getController() {
        return pageController;
    }

    @Override
    public ServiceDTOInterface<Page, String> getService() {
        return pageService;
    }

    @Override
    public Page buildElement(String id) {
        Page page = new Page();
        page.setUrl(id);
        return page;
    }

    @Override
    public String getCorrectID() {
        return "url";
    }

    @Override
    public String getIncorrectID() {
        return "FAKEURL";
    }

    @Override
    public Page buildElementWithEmptyID() {
        return buildElement(null);
    }
}
