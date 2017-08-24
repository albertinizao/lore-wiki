package com.opipo.rev.lorewiki;

import com.opipo.rev.lorewiki.repository.PageRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoreWikiApplicationTests {

	@Autowired
	private PageRepository pageRepository;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(pageRepository);
	}

}
