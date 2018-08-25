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


@RestController
@RequestMapping(value = "/")
public class Controller {
	

	

	
	@RequestMapping(value = "orderStatus/{number}/{customerId}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> orderStatus(@PathVariable("number") String OrderNo,@PathVariable("customerId") String custId) {
		   
	
        Trial tr=new Trial();
        Map<String,String> map=tr.retriveOrder(OrderNo,custId);
        return map;
       // Gson g=new Gson();
       
   //1000019446/0065167419
  //1000019446      
       
    }
	
/*	@RequestMapping(value = "orderStatus/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> orderStatus(@PathVariable("number") String OrderNo) 
	{
		Order od=new Order();
		Map<String,String> map=new HashMap<String,String>();
		map.put("order.status",od.getOrderState());
		return map;
		
	}*/
	
	
	@RequestMapping(value = "/getMobileNumber/{customerID}/{mobileNo}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> customerPhone(@PathVariable("customerID") String customerID,@PathVariable("mobileNo") String mobileNo ) 
	{
		Trial tr=new Trial();
        Map<String,String> map=tr.getCustomerContactDetails(customerID,mobileNo);
        return map;
		
	}
	
	

	@RequestMapping(value = "/getPostcode/{customerID}/{postCode}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> customerzip(@PathVariable("customerID") String customerID,@PathVariable("postCode") String postCode ) 
	{
		Trial tr=new Trial();
        Map<String,String> map=tr.getCustomerPostCodeDetails(customerID,postCode.replaceAll("\\s+",""));
        return map;
		
		
	}
	
	
	
	
	
	@RequestMapping(value = "/updateContactNumber/{customerID}/{mobileNo}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		   
	@ResponseBody
	public Map<String,String> updateContactNumber(@PathVariable("customerID") String customerID,@PathVariable("mobileNo") String mobileNo ) 
	{
		
		Trial tr=new Trial();
		
		
        Map<String,String> map=tr.setCustomerDetail(customerID, mobileNo, "");
        		
        return map;
		
		
	}
	
	@RequestMapping(value = "/updatePostcode/{customerID}/{postCode}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> udpateZipcode(@PathVariable("customerID") String customerID,@PathVariable("postCode") String postCode ) 
	{
		
		Trial tr=new Trial();
		
		
        Map<String,String> map=tr.setCustomerDetail(customerID, "", postCode);
        		
        return map;
		
		
	}
	
	
	   
	
	@RequestMapping(value = "cancelOrder/{number}/{customerId}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public Map<String,String> orderCancel(@PathVariable("number") String OrderNo,@PathVariable("customerId") String custId) {
		   
		System.out.println("Order To Cancel"+OrderNo);
		
		
        Trial tr=new Trial();
        Map<String,String> map=tr.orderCancel(OrderNo,custId);
       
       // Gson g=new Gson();
        return map;
        
       
    }
	
		

}