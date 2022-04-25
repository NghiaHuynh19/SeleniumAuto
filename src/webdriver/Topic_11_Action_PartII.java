package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Action_PartII {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name").toLowerCase();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		// 1. Khởi tạo action lên
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Right_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		// Scroll đến element này
		jsExecutor.executeScript("")

		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC_02_Double_Click() {
		
	}

	@Test
	public void TC_03_Drag_And_Drop_HTML4() {
		
	}

	@Test
	public void TC_04_Drag_And_Drop_HTML5_Css() {

	}

	@Test
	public void TC_05_Drag_And_Drop_HTML5_Xpath() {

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