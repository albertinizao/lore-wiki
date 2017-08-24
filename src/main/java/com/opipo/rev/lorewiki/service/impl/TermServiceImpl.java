package com.opipo.rev.lorewiki.service.impl;

import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.PageRepository;
import com.opipo.rev.lorewiki.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public List<String> getAllUrlFromTerm(String term) {
        return pageRepository.findByTerms(term).stream().map(f->f.getUrl()).collect(Collectors.toList());
    }

    @Override
    public String getUrlFromTerm(String term) {
        List<String> urls = this.getAllUrlFromTerm(term);
        return urls.isEmpty()?null:(urls.size()==1?urls.get(0):"multiple?urls="+urls.stream().collect(Collectors.joining("&")));
    }

    public String addLinksToPage(String pageName, String text){
        List<String> terms = new ArrayList<>();
        pageRepository.findAll().stream().filter(f->!f.getUrl().equalsIgnoreCase(pageName)).forEach(c->c.getTerms().stream().forEach(c2->terms.add(c2)));
        for (String term:terms){
            text=replaceAll(term,text);
        }
        return text;

    }

    public String replaceAll(String term, String text){
        text = replace(term,term, text);
        if (!term.toLowerCase().equals(term)) {
            text = replace(term.toLowerCase(), term, text);
        }
        if (!term.toUpperCase().equals(term)) {
            text = replace(term.toUpperCase(), term, text);
        }
        if (term.length()>1 && !(term.substring(0, 1).toUpperCase() + term.substring(1)).equals(term)) {
            text = replace(term.substring(0, 1).toUpperCase() + term.substring(1), term, text);
        }
        return text;
    }

    public String replace(String term, String termOriginal, String text){
        return text.replaceAll("\\b"+term+"\\b", "[url="+getUrlFromTerm(termOriginal)+"]"+term+"[/url]");
    }
}
