package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Khởi tạo sau khi driver này được sinh ra
		//JavascriptExcutor/ WebDriverWait/ Actions/...
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		// Khởi tạo  khi sử dụng (element xuất hiện)
		// Khởi tạo select để thao tác với element (country dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

		// Không support multiple select
		Assert.assertFalse(select.isMultiple());
		// Select giá trị Vietnam
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		
		//Verify Vietnam selected sucess
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

		driver.findElement(By.cssSelector("input#search_loc_submit")).click();
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32");
		
		//Java Generic
		List<WebElement> storeName = (List<WebElement>) driver.findElement(By.cssSelector("div#search_results div.store_name"));

		//Verify tổng số lượng store name = 32
		Assert.assertEquals(storeName.size(), 32);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}




		
	}

	@Test
	public void TC_02_NopCommerce() {
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}