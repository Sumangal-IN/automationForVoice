package com.tcs.automationForVoice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.tcs.automationForVoice.model.Cancel;
import com.tcs.automationForVoice.model.Customer;
import com.tcs.automationForVoice.model.Order;

import VoiceAutomationTest.Trial;

/**
 * @author biswad01
 *
 */
@RestController
@RequestMapping(value = "/")
public class Controller {
	
	
	/*@RequestMapping(value = "order/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Order order(@PathVariable("number") String OrderNo) {
		   
		
        Trial tr=new Trial();
        Order od=tr.RetrieveOrderDetail(OrderNo);
       // Gson g=new Gson();
        return od;
        
       
    }*/
	
	@RequestMapping(value = "order/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> order(@PathVariable("number") String OrderNo) {
		   
	
        Trial tr=new Trial();
        Map<String,String> map=tr.retriveOrder(OrderNo);
        return map;
       // Gson g=new Gson();
       
   
        
       
    }
	
	@RequestMapping(value = "orderStatus/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> orderStatus(@PathVariable("number") String OrderNo) 
	{
		Order od=new Order();
		Map<String,String> map=new HashMap<String,String>();
		map.put("order.status",od.getOrderState());
		return map;
		
	}
	
	@RequestMapping(value = "customerPhone", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public String customerPhone(@RequestBody Customer customer) 
	{
		System.out.println("customer phone"+customer.getCustomerMobile());
		Trial tr=new Trial();
		
        Customer cust=tr.setCustomerDetail(customer.getCustId(), customer.getCustomerMobile(), "");
        Gson g=new Gson();
        return g.toJson(cust.getUpdateMessage());
		
	}
	@RequestMapping(value = "customerPostcode", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public String customerPostcode(@RequestBody Customer customer) 
	{
		System.out.println("customer postcode"+customer.getCustPostCode());
		Trial tr=new Trial();
		
        Customer cust=tr.setCustomerDetail(customer.getCustId(),"",customer.getCustPostCode());
        Gson g=new Gson();
        return g.toJson(cust.getUpdateMessage());
		
	}
	
	   
	
	@RequestMapping(value = "OrderCancel/{number}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> orderCancel(@PathVariable("number") String OrderNo) {
		   
		System.out.println("sabya Order To Cancel"+OrderNo);
		System.out.println("length::"+OrderNo.length());
		System.out.println("match::"+OrderNo.matches("\\d+"));
		
        Trial tr=new Trial();
        Map<String,String> map=tr.orderCancel(OrderNo);
       
       // Gson g=new Gson();
        return map;
        
       
    }
	
		

}