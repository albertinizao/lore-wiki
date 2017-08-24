package com.opipo.rev.lorewiki.repository;

import com.opipo.rev.lorewiki.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PageRepository extends MongoRepository<Page, String> {

    List<Page> findByTerms(String term);

}
