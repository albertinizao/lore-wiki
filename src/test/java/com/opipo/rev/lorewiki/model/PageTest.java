package com.opipo.rev.lorewiki.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PageTest {

    private Page page;

    @Before
    public void init() {
        page = new Page();
    }


    @Test
    public void urlAttributeTest() {
        String url = Integer.toString(1);
        page.setUrl(url);
        assertEquals("The attribute is not the expected", url, page.getUrl());
    }


    @Test
    public void nameAttributeTest() {
        String name = Integer.toString(2);
        page.setName(name);
        assertEquals("The attribute is not the expected", name, page.getName());
    }


    @Test
    public void isPrivateAttributeTest() {
        Boolean isPrivate = Boolean.valueOf(true);
        page.setIsPrivate(isPrivate);
        assertEquals("The attribute is not the expected", isPrivate, page.getIsPrivate());
    }


    @Test
    public void givenSameObjReturnThatTheyAreEquals() {
        Page o1 = new Page();
        Page o2 = new Page();
        assertEquals("The object must be equals", o1, o2);
    }

    @Test
    public void givenSameObjReturnZero() {
        Page o1 = new Page();
        Page o2 = new Page();
        assertEquals("The object must be equals", 0, o1.compareTo(o2));
    }

    @Test
    public void givenObjectFromOtherClassReturnThatTheyArentEquals() {
        Page o1 = new Page();
        assertNotEquals("The object are from distinct classes", o1, new String());
    }

    @Test
    public void givenSameObjReturnSameHashCode() {
        Page o1 = new Page();
        Page o2 = new Page();
        assertEquals("The object must be equals", o1.hashCode(), o2.hashCode());
    }


}

