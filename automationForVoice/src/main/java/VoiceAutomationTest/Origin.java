package VoiceAutomationTest;


public enum Origin 
{
	GET_USER_DYN_URL("GetUserDynUrl","http://lnxs0506.uk.b-and-q.com:8080/dyn/admin/nucleus//atg/commerce/order/OrderRepository/"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX","//*[@name='xmltext']"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON","//*[@value='Enter']"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT","html/body/pre/code"),
	SENT_TO_OMS("SENT_TO_OMS","Order Just Submitted"),
	CREATED_TO_OMS("CREATED_TO_OMS","Sales Order Notification Sent in ATG"),
	PROCESSING("PROCESSING","Order Shipment processing started"),
	DISPATCHED("DISPATCHED","Order Ready for Shipment"),
	DELIVERED("DELIVERED","Order Shipped"),
	;
	
	
	private String key;
	private String value;
	
	Origin(String key,String value)
	{
		this.key=key;
		this.value=value;
				
	}
	
	
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
}

