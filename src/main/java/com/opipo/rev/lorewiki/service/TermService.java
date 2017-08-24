package com.opipo.rev.lorewiki.service;

import java.util.List;

public interface TermService {

    List<String> getAllUrlFromTerm(String term);

    String getUrlFromTerm(String term);
}
