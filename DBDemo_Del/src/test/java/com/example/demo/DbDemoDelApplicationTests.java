package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest

@RunWith(SpringRunner.class)
@DataJpaTest
class DbDemoDelApplicationTests {

	@Test
	void contextLoads() {
		assertThat(applicationRepo).isNotNull();
	}
	
	@Autowired
	private ApplicationRepo applicationRepo;
	
	@Test
	public void testFindByName() {
		List<Application> applications = applicationRepo
											.findByName("Java");
		assertEquals(1, applications.size());   // (expected, actual)
	}
	
	@Test
	public void testFindByRollNoGreaterThan() {
		List<Application> applications = applicationRepo
				.findByRollNoGreaterThan(1);
		
		assertEquals("Python", applications.get(0).getName());
		assertEquals(2, applications.get(0).getRollNo());
		
		assertEquals(".Net", applications.get(1).getName());
		assertEquals(3, applications.get(1).getRollNo());
	}
	
	

}
