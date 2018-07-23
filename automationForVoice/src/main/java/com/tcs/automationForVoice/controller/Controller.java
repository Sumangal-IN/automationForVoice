package com.tcs.automationForVoice.controller;

import org.openqa.selenium.WebDriver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tcs.automationForVoice.model.Order;

import VoiceAutomationTest.Trial;

@RestController
@RequestMapping(value = "/")
public class Controller {

	@RequestMapping(value = "orderStatus/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String teste(@PathVariable("number") String OrderNo) {

		Trial tr = new Trial();
		Order od = tr.RetrieveOrderDetail(OrderNo);
		System.out.println(od.getCreationDate());

		Gson gson = new Gson();
		return gson.toJson(od);
	}

}
