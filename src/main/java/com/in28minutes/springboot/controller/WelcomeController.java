package com.in28minutes.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.configuration.BasicConfiguration;
import com.in28minutes.springboot.service.WelcomeService;

@RestController
public class WelcomeController {

	//Auto wiring
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration configuration;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.retrieveWelcomeMessage();
	}
	
	@RequestMapping("/dynamicConfiguration")
	public Map dynamicConfiguration() {
		Map map = new HashMap();
		map.put("message", configuration.getMessage());
		map.put("number",configuration.getNumber());
		map.put("value", configuration.isValue());
		return map;
	}
}
