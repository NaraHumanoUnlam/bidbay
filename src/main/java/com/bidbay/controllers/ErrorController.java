package com.bidbay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/")
	  public void handleRequest() {
	      throw new RuntimeException("test exception");
	  }
}
