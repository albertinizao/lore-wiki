package com.opipo.rev.lorewiki.repository;

import com.opipo.rev.lorewiki.UnitTestApplicationConfig;
import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.repository.interfaces.MongoRepositoryComplete;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { UnitTestApplicationConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PageRepositoryIntegrationTest extends MongoRepositoryComplete<Page,String>{

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void autowiredCorrectly(){
		Assert.assertNotNull(mongoTemplate);
	}

	@Test
	public void findByTerm(){
		Assert.assertTrue(pageRepository.findByTerms("term")!=null);
	}

	@Test
	public void findByIncorrectTerm(){
		Assert.assertFalse(pageRepository.findByTerms("FAKE")!=null);
	}

	@Override
	public Page buildDocument() {
		return buildPage(getKey(), "name", false);
	}

	@Override
	public String getKey() {
		return "url";
	}

	@Override
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@Override
	public MongoRepository<Page, String> getMongoRepository() {
		return pageRepository;
	}

	@Override
	public Page buildNewDocument() {
		return buildPage(getKey()+" 1", "name 1", false);
	}

	@Override
	public Function<Page, Page> changeInfoInPersistedObject() {
		return p->buildPage(p.getUrl(),"cambiado", p.getIsPrivate());
	}

	private Page buildPage(String url, String name, Boolean isPrivate){
		Page page = new Page();
		page.setName(name);
		page.setUrl(url);
		page.setIsPrivate(isPrivate);
		page.setTerms(getTerms());
		return page;
	}

	private List<String> getTerms(){
		return Arrays.asList(new String[]{"term","term2"});
	}
}
