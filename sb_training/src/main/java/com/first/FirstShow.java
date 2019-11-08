package com.first;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstShow {

	@RequestMapping("/")
	public String index() {
		System.out.println("Hello!");
		return "hello";
	}

	@RequestMapping("/firstSubPath")
	public String hello() {
		System.out.println("firstSubPath");
		return "firstSubPath";
	}
	

}
