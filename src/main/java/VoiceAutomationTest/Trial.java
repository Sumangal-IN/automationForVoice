package VoiceAutomationTest;


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

	public Order RetrieveOrderDetail(String OrderNo)
	{

		WebDriver driver=initDriver();
		String queryResult="";
		String submitDate="";
		String creationDate="";
		String lastModifiedDate="";
		String orderState="";
		String orderOriginSource="";
		String totalToPay="";
		String paymentGroups="";
		String jurisdiction="";
		String stateDetail="";
		String error="There is an error while retrieving information";
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
		Order od=new Order();

		try
		{
			driver.get(Origin.GET_USER_DYN_URL.getValue());

			String initialOrderQuery="<query-items item-descriptor="+'"'+"order"+'"'+">sapOrderId="+'"'+OrderNo+'"'+"</query-items>";


			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
			driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
			submitDate = fetchValue(queryResult,"submittedDate");
			creationDate=fetchValue(queryResult,"creationDate");
			lastModifiedDate=fetchValue(queryResult,"lastModifiedDate");
			orderState=fetchValue(queryResult,"state");
			//orderState=fetchValue(queryResult,"status12");
			orderState=Origin.valueOf(orderState.toUpperCase()).getValue();
			orderOriginSource=fetchValue(queryResult,"orderOriginSource");
			totalToPay=fetchValue(queryResult,"totalToPay");
			paymentGroups=fetchValue(queryResult,"paymentGroups");
			jurisdiction=fetchValue(queryResult,"jurisdiction");
			stateDetail=fetchValue(queryResult,"stateDetail");
			error="";
		}
		catch(Exception e)
		{
			System.out.println("In catch!!!");
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


		driver.quit();

		return od;

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

	public Customer setCustomerDetail(String custId,String mobile,String postCode) 
	{
		String status="";
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
									status="Updated mobileNo";
								}
								else
								{
									status="Nothing to update mobileNo";
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
							status=status+"::thank you";
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
									status="Updated postCode";
								}
								else
								{
									status="Nothing to update postCode";
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
							status=status+"::thank you";
						}

					}
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("In Catch!!");
			e.printStackTrace();

		}
		Customer cust=new Customer();
		cust.setUpdateMessage(status);
		driver.quit();
		return cust;
	}
	public WebElement returnid(WebDriver driver,String id)
	{
		WebElement pathFix=driver.findElement(By.xpath(id));
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

	public Cancel orderCancel(String OrderId)
	{
		WebDriver driver=initDriver();
		//String amendableState="YES";
		String postCancellationState="";
		String postCancellationState_type="String";
		String error="";
		String error_type="String";
		String orderNo_type="String";
		Cancel cancel=new Cancel();

		try {
            driver.manage().window().maximize();
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

					if(returnid(driver,Origin.ORDER_ID_SEARCH_TEXTBOX.getValue()).isDisplayed())
					{
						returnid(driver,Origin.ORDER_ID_SEARCH_TEXTBOX.getValue()).sendKeys(OrderId);
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_ID_SEARCH_BUTTON.getValue()));
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].click();", returnid(driver,Origin.ORDER_ID_SEARCH_BUTTON.getValue()));

						waitId(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue());
						try {
							
							if(returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON_DISABLED.getValue()).isDisplayed())
							{
								//returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()).click();
								System.out.println("amend disabled");
								//amendableState="NO";
								
								
								error="Order is not in Cancellable state";
								postCancellationState="";
							}
						}
						catch(Exception e)
						{
							System.out.println("in catch!!!");
							e.printStackTrace();
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()));
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()));
							//returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()).click();
							System.out.println("clicked******");
							waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
							waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
							
						
							
						}
						
						if(driver.findElement(By.xpath(Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue())) != null)
						{
							System.out.println("dropdown present******");
							//returnid(driver,Origin.ORDER_AMENDMENT_AMEND_ORDER_BUTTON.getValue()).click();
							//waitId(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue());
							//if(returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue()).isDisplayed())
							
								returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN.getValue()).click();
								try{
								if(driver.findElement(By.xpath(Origin.ORDER_AMENDMENT_ACTION_DROPDOWN_CANCEL.getValue())) != null)
								{
								
								returnid(driver,Origin.ORDER_AMENDMENT_ACTION_DROPDOWN_CANCEL.getValue()).click();
								}
								}
								catch(Exception e)
								{
									System.out.println("no cancel option");
									error="Order is not in Cancellable state";
									postCancellationState="";
									driver.quit();
								}
								
								
									
								
								
								waitId(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_DROPDOWN.getValue());

								returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_DROPDOWN.getValue()).click();
								
								returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_MODAL_REASON_SELECT.getValue()).click();
								waitId(driver,Origin.ORDER_AMENDMENT_CANCEL_SAVE_BUTTON.getValue());
								returnid(driver,Origin.ORDER_AMENDMENT_CANCEL_SAVE_BUTTON.getValue()).click();
								waitId(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue());
								((JavascriptExecutor) driver).executeScript(
										"arguments[0].scrollIntoView(true);", returnid(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue()));
								((JavascriptExecutor) driver).executeScript(
										"arguments[0].click();", returnid(driver,Origin.ORDER_AMENDMENT_PROCEED_TO_CHKOUT.getValue()));
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
								         error="";
								         postCancellationState="Cancelled Order";
								         
										//postCancellationState="Cancelled Order";
									}
								}




							

						}
						else
						{
							error="Order is not in Cancellable state";
							postCancellationState="";
						}


					}
						
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		cancel.setOrderNo(OrderId);
		cancel.setOrderNo_type(orderNo_type);
		cancel.setError(error);
		cancel.setError_type(error_type);
		//cancel.setAmendableState(amendableState);
		cancel.setPostCancellationState(postCancellationState);
		cancel.setPostCancellationState_type(postCancellationState_type);
		
		driver.quit();
		
		return cancel;
	}
}