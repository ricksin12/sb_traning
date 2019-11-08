package com.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstHtml {
	@RequestMapping("/firstHtml")
	public String firstHtml() {
		System.out.println("firstHtml");
		return "firstHtml";
	}

	@RequestMapping("/firstSubmit")
	public String firstSubmit() {
		System.out.println("firstSubmit");
		return "firstSubmit";
	}

	@RequestMapping("/firstReceiveSubmit")
	public String firstReceiveSubmit() {
		System.out.println("/firstReceiveSubmit");
		return "firstReceiveSubmit";
	}

}
