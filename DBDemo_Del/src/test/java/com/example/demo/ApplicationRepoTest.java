package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ApplicationRepoTest {
	
	@InjectMocks
	private DBController controller;

	@Mock
	private ApplicationRepo repo;  //mock the data
	
	@Test
	public void retrieveAllApplicationsTest() {
		List<Application> appList = new ArrayList<Application>();
		
		Application app1 = new Application();
		app1.setRollNo(1);
		app1.setName("Java");
		
		Application app2 = new Application();
		app2.setRollNo(2);
		app2.setName("Python");
		
		appList.add(app1);
		appList.add(app2);
		
		when(repo.findAll()).thenReturn(appList);
		
		List<Application> testApplicationList = controller.getAllApplications();
		assertEquals(1, testApplicationList.get(0).getRollNo());
		assertEquals("Java", testApplicationList.get(0).getName());
		
		
		assertEquals(2, testApplicationList.get(1).getRollNo());
		assertEquals("Python", testApplicationList.get(1).getName());
		
	}
	
}
