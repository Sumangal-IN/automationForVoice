package VoiceAutomationTest;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.tcs.automationForVoice.model.Cancel;
import com.tcs.automationForVoice.model.Customer;
import com.tcs.automationForVoice.model.Order;



public class Trial {

	public WebDriver initDriver()
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:/downloads/chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public Order RetrieveOrderDetail(String OrderNo, String custId)
	{

		WebDriver driver=initDriver();
		String queryResult="";
		String submitDate="";
		String creationDate="";
		String lastModifiedDate="";
		String orderState="";
		String orderStateOriginal="";
		String orderOriginSource="";
		String totalToPay="";
		String paymentGroups="";
		String jurisdiction="";
		String stateDetail="";
		String orderStatusSuccessStatus="true";
		String error="";
		String submitDate_type="date";
		String creationDate_type="date";
		String lastModifiedDate_type="date";
		String orderState_type="string";
		String orderOriginSource_type="string";
		String totalToPay_type="string";
		String paymentGroups_type="string";
		String jurisdiction_type="string";
		String stateDetail_type="string";
		String error_type="string";
		String cust_rel_order="";
		String cust_rel_order_type="string";
		String orderStatusSuccessStatus_type="String";
		Order od=new Order();

		try
		{
			driver.get(Origin.GET_USER_DYN_URL.getValue());

			String initialOrderQuery="<query-items item-descriptor="+'"'+"order"+'"'+">sapOrderId="+'"'+OrderNo+'"'+"</query-items>";


			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
			driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();

			if(!queryResult.contains("No items returned"))
			{

				orderStatusSuccessStatus="true";

				error="";
				cust_rel_order=this.AssociationCheck(driver,orderStatusSuccessStatus,error,queryResult, OrderNo, custId);
				System.out.println("******************cust_rel_order"+cust_rel_order);
				if(!cust_rel_order.contains("invalid"))
				{
					submitDate = fetchValue(queryResult,"submittedDate");
					creationDate=fetchValue(queryResult,"creationDate");
					lastModifiedDate=fetchValue(queryResult,"lastModifiedDate");
					orderStateOriginal=fetchValue(queryResult,"state");
					//orderState=fetchValue(queryResult,"status12");
					orderState=Origin.valueOf(orderStateOriginal.toUpperCase()).getValue();
					orderOriginSource=fetchValue(queryResult,"orderOriginSource");
					totalToPay=fetchValue(queryResult,"totalToPay");
					paymentGroups=fetchValue(queryResult,"paymentGroups");
					jurisdiction=fetchValue(queryResult,"jurisdiction");
					stateDetail=fetchValue(queryResult,"stateDetail");
				}
				System.setProperty(OrderNo+"_State", orderStateOriginal);
				System.out.println("*********properties file value::::"+System.getProperty(OrderNo+"_State"));


				/*if(cust_rel_order.contains("invalid"))
			{
				submitDate = "";
				creationDate="";
				lastModifiedDate="";
				orderState="";
				//orderState=fetchValue(queryResult,"status12");
				orderState="";
				orderOriginSource="";
				totalToPay="";
				paymentGroups="";
				jurisdiction="";
				stateDetail="";
				orderStatusSuccessStatus="true";
				error="";

			}*/
			}
			else
			{
				error="";
				orderStatusSuccessStatus="true";
				cust_rel_order="false";
			}
		}


		catch(Exception e)
		{
			System.out.println("In catch!!!");
			error="Technical Error";
			orderStatusSuccessStatus="false";
			cust_rel_order="";
			e.printStackTrace();
		}


		od.setSubmitDate(submitDate);
		od.setCreationDate(creationDate);
		od.setLastModifiedDate(lastModifiedDate);
		od.setOrderState(orderState);
		od.setJurisdiction(jurisdiction);
		od.setOrderOriginSource(orderOriginSource);
		od.setPaymentGroups(paymentGroups);
		od.setStateDetail(stateDetail);
		od.setTotalToPay(totalToPay);
		od.setError(error);
		od.setSubmitDate_type(submitDate_type);
		od.setCreationDate_type(creationDate_type);
		od.setLastModifiedDate_type(lastModifiedDate_type);
		od.setOrderState_type(orderState_type);
		od.setJurisdiction_type(jurisdiction_type);
		od.setOrderOriginSource_type(orderOriginSource_type);
		od.setPaymentGroups_type(paymentGroups_type);
		od.setStateDetail_type(stateDetail_type);
		od.setTotalToPay_type(totalToPay_type);
		od.setError_type(error_type);
		if(cust_rel_order.contains("invalid"))
		{
			cust_rel_order="false";
		}

		od.setCust_rel_order(cust_rel_order);
		od.setCust_rel_order_type(cust_rel_order_type);
		od.setOrderSuceessStatus(orderStatusSuccessStatus);
		od.setOrderSuceessStatus_type(orderStatusSuccessStatus_type);


		driver.quit();

		return od;

	}

	public String AssociationCheck(WebDriver driver,String successStatus,String error,String queryResult,String Order,String custId)
	{
		String profileId="";
		String newQueryResult="";
		String extCustId="";
		String flag="false";

		error="";
		successStatus="true";
		profileId=this.fetchValue(queryResult, "profileId");

		try
		{

			driver.get(Origin.GET_USER_PROFILE__DYN_URL.getValue());
			String initialOrderQuery="<query-items item-descriptor="+'"'+"user"+'"'+">id="+'"'+profileId+'"'+"</query-items>";


			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
			driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			newQueryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
			if(!newQueryResult.contains("No items returned"))
			{
				extCustId=this.fetchValue(newQueryResult, "externalId");
				System.out.println("ext Iddd"+extCustId);
				if(extCustId.trim().equals(custId))
				{
					flag="true";
				}
				else
				{
					flag="invalid customer";
				}
			}


		}
		catch(Exception e)
		{
			successStatus="false";
			error="Technical Error";
		}

		System.out.println("*******************FLAG::::"+flag);

		return flag;

	}

	public Map<String,String> retriveOrder(String Order,String custId)
	{
		Order od=this.RetrieveOrderDetail(Order,custId);
		Map<String,String> map=new HashMap<String,String>();

		map.put("order.orderNumber", Order);
		map.put("customer.customerId", custId);
		map.put("order.creationDate", od.getCreationDate());
		map.put("order.totalToPay", od.getTotalToPay());
		map.put("order.submitDate", od.getSubmitDate());
		map.put("order.lastModifiedDate", od.getLastModifiedDate());
		map.put("order.orderState", od.getOrderState());
		map.put("order.paymentGroups", od.getPaymentGroups());
		map.put("order.jurisdiction", od.getJurisdiction());
		map.put("order.stateDetail", od.getStateDetail());
		map.put("orderStatus.success", od.getOrderSuceessStatus());
		map.put("orderStatus.failureReason", od.getError());
		map.put("order.creationDate.type", od.getCreationDate_type());
		map.put("order.totalToPay.type", od.getTotalToPay_type());
		map.put("order.submitDate.type", od.getSubmitDate_type());
		map.put("order.lastModifiedDate.type", od.getLastModifiedDate_type());
		map.put("order.orderState.type", od.getOrderState_type());
		map.put("order.paymentGroups.type", od.getPaymentGroups_type());
		map.put("order.jurisdiction.type", od.getJurisdiction_type());
		map.put("order.stateDetail.type", od.getStateDetail_type());
		map.put("relation.order.orderNumber.customer.customerID", od.getCust_rel_order());
		map.put("relation.order.orderNumber.customer.customerID.type", od.getCust_rel_order_type());
		map.put("orderStatus.failureReason.type",od.getError_type());
		map.put("orderStatus.success.type", od.getOrderSuceessStatus_type());


		System.out.println("********"+od.getError()+od.getError().length());
		/*if(od.getError().length()>0)
		{
			map.put("orderStatus.failureReason","True");
			map.put("orderStatus.failureReason",od.getError_type());
		}*/

		return map;
	}

	public String fetchValue(String queryResult,String value) {
		String result = null;
		if (!queryResult.contains("No items returned")) {
			if(queryResult.contains(value))
			{
				result = queryResult.split(value + '"' + "><!" + "\\["
						+ "CDATA" + "\\[")[1].split("]")[0].trim();
			}
			else
			{
				result=value.equals("state")?"FIELDMISSING":"";
			}


		}
		else
		{
			result=value.equals("state")?"NOTFOUND":"";
		}
		return result;

	}

	public Map<String,String> setCustomerDetail(String custId,String mobile,String postCode) 
	{
		String successStatus="";
		String failureReason="";
		WebDriver driver=initDriver();

		try {

			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

			driver.get(Origin.GET_USER_AGENT_URL.getValue());
			if(returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).isDisplayed())
			{
				returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).sendKeys(Origin.AGENT_USERID.getValue());
				returnid(driver,Origin.PASSWORD_FIELD.getValue()).sendKeys(Origin.AGENT_PASSWORD.getValue());

				returnid(driver,Origin.LOGIN_BUTTON.getValue()).click();
				if(returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).isDisplayed())
				{
					returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).sendKeys(Origin.CONTACT_CENTER_LOACTION.getValue());
					returnid(driver,Origin.CONTACT_CENTER_CONFIRM_LOCATION.getValue()).click();

					waitId(driver,Origin.CONTACT_CENTER_LOACTION_CONTINUE.getValue());

					returnid(driver,Origin.CONTACT_CENTER_LOACTION_CONTINUE.getValue()).click();

					if(returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_FIELD.getValue()).isDisplayed())
					{
						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_FIELD.getValue()).sendKeys(custId);
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP.getValue()));
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].click();", returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP.getValue()));
						Thread.sleep(5000);

						waitId(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_EDIT.getValue());

						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-2000)", "");

						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_EDIT.getValue()).click();

						waitId(driver,Origin.CONTACT_CENTER_SCROLL_DOWN.getValue());


					}
					System.out.println(mobile.length()+mobile); 
					// final Actions builder = new Actions(driver);
					if(mobile.length()>0) {
						System.out.println("inside if mobile");
						for(int i=1;i<=20;i++)
						{
							if(returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).isDisplayed())
							{
								if(!returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).getAttribute("value").equals(mobile))
								{
									returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).clear();

									returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).sendKeys(mobile);
									successStatus="";
								}
								else
								{
									successStatus="";
								}

								break;
							}
							else
							{
								returnid(driver,Origin.CONTACT_CENTER_SCROLL_DOWN.getValue()).click();
							}
						}

						waitId(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE.getValue());

						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE.getValue()).click();



						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-2000)", "");
						Thread.sleep(3000);
						waitId(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_REFLECT.getValue());
						String newNumber=returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_REFLECT.getValue()).getText();
						System.out.println("***"+newNumber);
						if(newNumber.equals(mobile))
						{
							successStatus="true";
						}
					}
					if(postCode.length()>0)
					{

						System.out.println("inside if post");
						for(int i=1;i<=20;i++)
						{

							if(returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).isDisplayed())
							{
								if(!returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).getAttribute("value").equals(postCode))
								{
									returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).clear();

									returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).sendKeys(postCode);
									successStatus="";
								}
								else
								{
									successStatus="";
								}

								break;
							}
							else
							{
								returnid(driver,Origin.CONTACT_CENTER_SCROLL_DOWN.getValue()).click();
							}
						}

						waitId(driver,Origin.CONTACT_CENTER_POSTCODE.getValue());

						returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).sendKeys(Keys.TAB);
						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE.getValue()).click();

						//waitId(driver,Origin.CONTACT_CENTER_POSTCODE_CHANGE.getValue());

						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-2000)", "");

						//waitId(driver,Origin.CONTACT_CENTER_POSTCODE_CHANGE.getValue());
						Thread.sleep(3000);
						String newPostCode=returnid(driver,Origin.CONTACT_CENTER_POSTCODE_CHANGE.getValue()).getText();
						System.out.println("***"+newPostCode);
						if(newPostCode.contains(postCode))
						{
							successStatus="true";
						}

					}
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("In Catch!!");
			e.printStackTrace();
			successStatus="false";
			failureReason="a technical error occured";

		}
		
		
		Customer cust=new Customer();
		cust.setUpdateSuccessStatus(successStatus);
		cust.setUpdateFailureReason(failureReason);
		Map<String,String> map=new HashMap<String,String>();
		map.put("customer.customerID", custId);
		if(mobile.length()>0)
		{
			map.put("customer.mobileNumber", mobile);
			map.put("updateMobileNumber.success", cust.getUpdateSuccessStatus());
			map.put("updateMobileNumber.failureReason", cust.getUpdateFailureReason());
		}
		if(postCode.length()>0)
		{
			map.put("customer.postCode", postCode);
			map.put("udpatepostCode.success", cust.getUpdateSuccessStatus());
			map.put("udpatepostCode.failureReason", cust.getUpdateFailureReason());
		}
		
		
		driver.quit();
		return map;
	}
	public WebElement returnid(WebDriver driver,String id)
	{
		WebElement pathFix=null;
		try{
			pathFix=driver.findElement(By.xpath(id));
		}
		catch(Exception ex) {
			pathFix=null;
		}
		return pathFix;
	}

	/**
	 * Handling Explicit wait
	 * @param driver
	 * @param id
	 * @return
	 */
	public WebElement waitId(WebDriver driver,String id)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		WebElement pathFix=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));

		return pathFix;
	}

	/*public static void main(String args[])
{

	Trial tr=new Trial();

	//tr.setCustomerDetail("0065178655","07789562341", null);
	System.out.println(tr.setCustomerDetail("0065178655","07714527890", "").getUpdateMessage());


}*/

	public Map<String,String> orderCancel(String OrderId,String custId)
	{

		WebDriver driver=initDriver();
		//String amendableState="YES";
		String success="true";
		String postCancellationState_type="String";
		String reasonOrderNotCancellable="";
		String cancellable="";
		String failureReason="";
		String error="";
		String error_type="String";
		String orderNo_type="String";
		Cancel cancel=new Cancel();
		String orderState=System.getProperty(OrderId+"_State");
		System.out.println("order state*****"+orderState);

		if(orderState.equals("CREATED_IN_OMS")){

			try {
				//String regex="//d+";
				//System.out.println("length::"+OrderId.length());
				//System.out.println("match::"+OrderId.matches(regex));


				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

				driver.get(Origin.GET_USER_AGENT_URL.getValue());
				if(OrderId.length()==10 )//&& OrderId.matches(regex))
				{
					if(returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).isDisplayed())
					{
						returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).sendKeys(Origin.AGENT_USERID.getValue());
						returnid(driver,Origin.PASSWORD_FIELD.getValue()).sendKeys(Origin.AGENT_PASSWORD.getValue());

						returnid(driver,Origin.LOGIN_BUTTON.getValue()).click();
						if(returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).isDisplayed())
						{
							returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).sendKeys(Origin.CONTACT_CENTER_LOACTION.getValue());
							returnid(driver,Origin.CONTACT_CENTER_CONFIRM_LOCATION.getValue()).click();

							waitId(driver,Origin.CONTACT_CENTER_LOACTION_CONTINUE.getValue());

							returnid(driver,Origin.CONTACT_CENTER_LOACTION_CONTINUE.getValue()).click();

							if(returnid(driver,Origin.ORDER_ID_SEARCH_TEXTBOX.getValue()).isDisplayed())
							{
								returnid(driver,Origin.ORDER_ID_SEARCH_TEXTBOX.getValue()).sendKeys(OrderId);
								((JavascriptExecutor) driver).executeScript(
										"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_ID_SEARCH_BUTTON.getValue()));
								((JavascriptExecutor) driver).executeScript(
										"arguments[0].click();", returnid(driver,Origin.ORDER_ID_SEARCH_BUTTON.getValue()));


								if(returnid(driver,Origin.ORDER_NOT_FOUND_ERROR.getValue())==null){	
									JavascriptExecutor jse = (JavascriptExecutor)driver;
									jse.executeScript("window.scrollBy(0,-2000)", "");							
									waitId(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue());


									System.out.println("inside else");
									((JavascriptExecutor) driver).executeScript(
											"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()));
									((JavascriptExecutor) driver).executeScript(
											"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()));
									//returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()).click();
									System.out.println("clicked******");
									waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
									waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
									//	}


									//JavascriptExecutor jse = (JavascriptExecutor)driver;
									jse.executeScript("window.scrollBy(0,-2000)", "");

									if(driver.findElement(By.xpath(Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue())) != null)
									{
										System.out.println("dropdown present******");
										//returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()).click();
										//waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
										//if(returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue()).isDisplayed())
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue()));
										//		returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue()).click();
										try{
											if(driver.findElement(By.xpath(Origin.ORDER_AMENDMENT_ACTION_DROPDOWN_CANCEL.getValue())) != null)
											{

												returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN_CANCEL.getValue()).click();
											}
										}
										catch(Exception e)
										{
											System.out.println("no cancel option");
											reasonOrderNotCancellable="Order is not in Cancellable state";
											cancellable="false";
											failureReason="";
											driver.quit();
										}


										waitId(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_DROPDOWN.getValue());
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_DROPDOWN.getValue()));
										//returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_DROPDOWN.getValue()).click();

										returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_SELECT.getValue()).click();
										Thread.sleep(3000);
										returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_SAVE_BUTTON.getValue()).click();
										waitId(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue());
										Thread.sleep(3000);
										//jse.executeScript("window.scrollBy(0,-2000)", "");
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue()));

										//returnid(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue()).click();
										//returnid(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue()).click();
										waitId(driver,Origin.ORDER_AMENDMENT_REFUND_PLACE_BUTTON.getValue());
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_AMENDMENT_REFUND_PLACE_BUTTON.getValue()));
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_REFUND_PLACE_BUTTON.getValue()));

										waitId(driver,Origin.ORDER_AMENDMENT_ORDER_MODAL.getValue());
										if(returnid(driver,Origin.ORDER_AMENDMENT_ORDER_MODAL.getValue()).isDisplayed())
										{
											if(returnid(driver,Origin.ORDER_AMENDMENT_ORDER_MODAL.getValue()).getText().contains("cancelled"))
											{
												System.out.println("*****************Order Cacelled");																					
												cancellable="true";
												//reasonOrderNotCancellable="";

											}
										}

									}
									else
									{
										error="Order is not in Cancellable state";										
										cancellable="false";
									}
								}
							}
							else
							{
								error="No Search Record Found";
							}
						}
					}
					else {
						success="false";
						failureReason="technical error";
					}
				}
				else
				{
					error="Invalid Order Number";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			if(orderState.equals("REMOVED"))
			{
				reasonOrderNotCancellable="Order is Already Cancelled";
				cancellable="false";				
			}
			else {

				reasonOrderNotCancellable="Order is not in Amendable State";
				cancellable="false";
			}
		}


		cancel.setOrderNo(OrderId);
		cancel.setOrderNo_type(orderNo_type);

		cancel.setCancellable(cancellable);
		cancel.setReasonOrderNotCancellable(reasonOrderNotCancellable);
		cancel.setSuccess(success);
		cancel.setFailureReason(failureReason);

		Map<String,String> cancelmap=new HashMap<String,String>();
		cancelmap.put("order.orderNumber",cancel.getOrderNo());
		//cancelmap.put("order.orderNumber_type",cancel.getOrderNo_type());
		cancelmap.put("customer.customerId",custId);

		cancelmap.put("orderCancel.cancelled", cancel.getCancellable());
		//cancelmap.put("orderCancel.cancelled_type", cancel.getCancellable_type());
		cancelmap.put("orderCancel.reasonOrderNotCancelled", cancel.getReasonOrderNotCancellable());
		//cancelmap.put("orderCancel.reasonOrderNotCancelled_type", cancel.getReasonOrderNotCancellable_type());
		cancelmap.put("orderCancel.success",cancel.getSuccess());
		//cancelmap.put("orderCancel.success",cancel.getSuccess_type());
		cancelmap.put("orderCancel.failureReason",cancel.getFailureReason());
		//cancelmap.put("orderCancel.failureReason.type",cancel.getFailureReason_type());


		System.clearProperty(OrderId+"_State");
		System.out.println("order state after clearing property file"+System.getProperty(OrderId+"_State"));
		driver.quit();

		return cancelmap;
	}


	public Map<String,String> getCustomerContactDetails(String custId,String phoneNumber)
	{
		WebDriver driver=initDriver();
		String queryResult="";
		String currentPhnumber="";
		String cust_Rel_Phn="false";
		String success="true";
		String failureReason="";
		//String queryResult_type="String";
		//String currentPhnumber_type="String";
		String cust_Rel_Phn_type="boolean";
		String success_type="boolean";
		String failureReason_type="String";
		Customer cust=new Customer();
		try{

			driver.get(Origin.GET_USER_PROFILE__DYN_URL.getValue());

			String initialOrderQuery="<query-items item-descriptor="+'"'+"user"+'"'+">externalId="+'"'+custId+'"'+"</query-items>";


			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
			driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
			if(!queryResult.contains("No items returned"))
			{
				currentPhnumber=fetchValue(queryResult,"mobile").trim();
				if(currentPhnumber.equals(phoneNumber))
				{
					cust_Rel_Phn="true";
					

				}

			}
		
		}
		catch(Exception ex)
		{
			failureReason="Technical Error";
			success="false";
			cust_Rel_Phn="";
		}

		cust.setFailureReason(failureReason);
		cust.setFailureReason_type(failureReason_type);
		cust.setSuccess(success);
		cust.setSuccess_type(success_type);
		cust.setCust_rel_Phn(cust_Rel_Phn);
		cust.setCust_rel_Phn_type(cust_Rel_Phn_type);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("customer.customerID", custId);
		map.put("customer.mobileNumber", phoneNumber);
		map.put("relation.customer.customerID.customer.mobileNumber", cust.getCust_rel_Phn());
		//map.put("relation.customer.customerID.customer.mobileNumber.type", cust.getCust_rel_Phn_type());
		map.put("getContact.success",cust.getSuccess());
		//map.put("getContact.success.type",cust.getSuccess_type());
		map.put("getContact.failureReason",cust.getFailureReason());
		//map.put("getContact.failureReason.type",cust.getFailureReason_type());
		
		driver.quit();
		return map;
	}

	public Map<String,String> getCustomerPostCodeDetails(String custId,String postCode)
	{
		WebDriver driver=initDriver();
		String queryResult="";
		String currentPostcode="";
		String cust_Rel_postcode="false";
		String success="true";
		String failureReason="";
		//String queryResult_type="String";
		//String currentPhnumber_type="String";
		String cust_Rel_postcode_type="boolean";
		String success_type="boolean";
		String failureReason_type="String";
		String id="";
		String queryResultpostcode="";
		String custzipcode="";
		Customer cust=new Customer();
		try{

			driver.get(Origin.GET_USER_PROFILE__DYN_URL.getValue());
			

			String initialOrderQuery="<query-items item-descriptor="+'"'+"user"+'"'+">externalId="+'"'+custId+'"'+"</query-items>";
			


			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
			driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
			
			if(!queryResult.contains("No items returned"))
			{
				id=fetchValue(queryResult,"homeAddress").trim();
				System.out.println("***Homeaddress id****"+id);
				driver.get(Origin.GET_USER_PROFILE__DYN_URL.getValue());
				String postCodeQuery="<query-items item-descriptor="+'"'+"contactInfo"+'"'+">id="+'"'+id+'"'+"</query-items>";
				
				//driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())).clear();
				((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), postCodeQuery);
				
				driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				//Thread.sleep(60000);
				queryResultpostcode = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
				custzipcode=fetchValue(queryResultpostcode,"postalCode").replaceAll("\\s+","");
				System.out.println("***postal code ****"+custzipcode);
				
				if(!queryResultpostcode.contains("No items returned"))
				if(custzipcode.equals(postCode))
				{
					cust_Rel_postcode="true";
					

				}

			}
		
		}
		catch(Exception ex)
		{
			failureReason="Technical Error";
			success="false";
			cust_Rel_postcode="";
		}

		cust.setFailureReason(failureReason);
		cust.setFailureReason_type(failureReason_type);
		cust.setSuccess(success);
		cust.setSuccess_type(success_type);
		cust.setCust_rel_Postcode(cust_Rel_postcode);
		cust.setCust_rel_Postcode_type(cust_Rel_postcode_type);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("customer.customerID", custId);
		map.put("customer.postcode", postCode);
		map.put("relation.customer.customerID.customer.postCode", cust.getCust_rel_Postcode());
		//map.put("relation.customer.customerID.customer.postCode.type", cust.getCust_rel_Postcode_type());
		map.put("getPostcode.success",cust.getSuccess());
		//map.put("getPostcode.success.type",cust.getSuccess_type());
		map.put("getPostcode.failureReason",cust.getFailureReason());
		//map.put("getPostcode.failureReason.type",cust.getFailureReason_type());
		
		driver.quit();
		return map;
	}


}