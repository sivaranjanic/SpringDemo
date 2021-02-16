package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController   // = @Controller + @ResponseBody
public class DBController {

	@Autowired
	ApplicationRepo repo;	

	
//	@RequestMapping ("/")
//	public ModelAndView home() {
//		System.out.println("in home");
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("login.jsp");
//		return mv;
//		
//	}
	
	@RequestMapping ("/")
	public ModelAndView home(Application app) {
		System.out.println("in home");
		ModelAndView mv = new ModelAndView();  
		mv.addObject("obj", app);
		mv.setViewName("home.jsp");
		
		
//		HttpServletRequest req = new HttpServletRequest();
//		req.
		
		
		return mv;
		
	}
	
	@RequestMapping("/getData")
	public ModelAndView getData(@RequestParam int rollNo) {
		
		System.out.println("Get data method called");
		
		Optional<Application> app = repo.findById(rollNo);  //  select * from Application where id = "";
		
		
		// Java 8 -- Optional -- If Application obj is null, it will give an empty Application obj to avoid null pointer exception
		// before 8 --  Application app  = repo.findById(rollNo).orElse(new Application());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", app);
		mv.setViewName("getData.jsp");
		
		System.out.println("find by name :" +repo.findByName("Java"));
		
		System.out.println("find by id greater than 2 :" +repo.findByRollNoGreaterThan(2));
		
		
		return mv;
	}
	
	
	
	
	@RequestMapping("/addData")
	public ModelAndView addData(Application app) {
		System.out.println("Add data method called");
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", app);
		mv.setViewName("home.jsp");
		repo.save(app);    // insert  &  commit
		return mv;
	}
	
	
	@RequestMapping("/applications")
//	@ResponseBody
	public String getApplications() {
		System.out.println("get applications");
		return repo.findAll().toString();
	}
	
	
	@RequestMapping("/allApplications")
	public List<Application> getAllApplications() {
		System.out.println("get applications");
		List<Application> allApplications =  repo.findAll();
		return allApplications;
	}
	
	@RequestMapping("/application/{rollNo}")   //	@RequestMapping("/getData")
//	@ResponseBody
	public String getApplicationByRollNo(@PathVariable int rollNo) {
		return repo.findById(rollNo).toString();
	}
	
	
	@GetMapping(path = "/getApplications")  // @RequestMapping(method="GET" , produces = "{"application/json"}")
//	@ResponseBody
	public String getApplicationsByGet() {
		System.out.println("By get method");
		return repo.findAll().toString();
	}
	
	
	@PostMapping(path = "/saveApplication" , consumes = {"application/json"})
	@ResponseBody
	public String postApplications(@RequestBody Application app) {
		System.out.println("In post call");
		repo.save(app);
		return "Data inserted";
	}

//	@Transactional (noRollbackFor = NumberFormatException.class, timeout = 100)  // multiple exceptions can be added
	@PostMapping(path = "/saveTransaction" , consumes = {"application/json"})
	@ResponseBody
	public String postTransaction(@RequestBody Application app) {
		System.out.println("In post call");
		repo.save(app);
		
		int a = 0;
		if (a == 0) { 
			throw new NumberFormatException();
		}
		
//		app.setName("Siva");
//		repo.save(app);
		return "Data inserted";
	}
	
	@DeleteMapping(path = "/deleteApplication/{rollNo}")
	@ResponseBody
	public String deleteApplication(@PathVariable int rollNo) {
		System.out.println("In delete call");
		Application app = repo.getOne(rollNo); 
		
		if (app.getRollNo() == 1) {
			throw new ApplicationNotFoundException();    // custom exception
		} else if(app.getRollNo() == 3) {
			throw new NullPointerException();		// java generic exception
		}
		
		repo.delete(app);
		return "Data with id " + rollNo + " deleted";
	}
	
	@PutMapping(path = "/putApplication" , consumes = {"application/json"})
//	@ResponseBody
	public Application putApplication(@RequestBody Application app) {
		System.out.println("In put call");
		repo.save(app);
		return app;
	}
}
