package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get  요청: " + m.getId() + ", " + m.getUsername();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		Member m1 =  Member.builder().username("dan").build();
		return "post  요청: "+  m.getId() + ", " + m.getUsername();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put  요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete  요청";
	}
	
}
