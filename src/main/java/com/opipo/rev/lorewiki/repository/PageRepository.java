package com.opipo.rev.lorewiki.repository;

import com.opipo.rev.lorewiki.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {

}
