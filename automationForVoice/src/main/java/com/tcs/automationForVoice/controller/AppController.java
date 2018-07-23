package com.tcs.automationForVoice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tcs.automationForVoice.model.OrderStatus;

@Controller
public class AppController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String greeting() {
		return "hello world";
	}

//	@RequestMapping(value = "/orderStatus/{orderNumber}", method = RequestMethod.GET, produces = { "application/json" })
//	@ResponseBody
//	public String attendCall(@PathVariable("orderNumber") String orderNumber) {
//		Gson gson = new Gson();
//		// Your code goes here
//		return gson.toJson(new OrderStatus(orderNumber));
//	}
}
