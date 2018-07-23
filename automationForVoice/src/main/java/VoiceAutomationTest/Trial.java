package VoiceAutomationTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tcs.automationForVoice.model.Order;

public class Trial {

	public WebDriver initDriver() {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "G:/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	public Order RetrieveOrderDetail(String OrderNo) {
		WebDriver driver = initDriver();
		String queryResult = null;
		String submitDate = null;
		String creationDate = null;
		String lastModifiedDate = null;
		String orderState = null;

		driver.get(Origin.GET_USER_DYN_URL.getValue());

		String initialOrderQuery = "<query-items item-descriptor=" + '"' + "order" + '"' + ">sapOrderId=" + '"' + OrderNo + '"' + "</query-items>";

		Order od = new Order();

		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
		driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
		submitDate = fetchValue(queryResult, "submittedDate");
		creationDate = fetchValue(queryResult, "creationDate");
		lastModifiedDate = fetchValue(queryResult, "lastModifiedDate");
		orderState = fetchValue(queryResult, "state");
		orderState = Origin.valueOf(orderState.toUpperCase()).getValue();

		od.setSubmitDate(submitDate);
		od.setCreationDate(creationDate);
		od.setLastModifiedDate(lastModifiedDate);
		od.setOrderState(orderState);

		driver.quit();

		return od;

	}

	public String fetchValue(String queryResult, String value) {
		String result = null;
		if (!queryResult.contains("No items returned")) {
			if (queryResult.contains(value)) {
				result = queryResult.split(value + '"' + "><!" + "\\[" + "CDATA" + "\\[")[1].split("]")[0].trim();
			}

		}
		return result;

	}

//	public static void main(String args[]) {
//		Trial t = new Trial();
//		Order o = t.RetrieveOrderDetail("1000016295");
//		System.out.println(o.getCreationDate());
//	}

}
