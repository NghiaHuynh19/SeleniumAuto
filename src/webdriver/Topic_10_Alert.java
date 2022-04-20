package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_00_Login_Empty_Data(){
		driver.get("https://demo.guru99.com/V4/index.php");

		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "User or Password is not valid");

		alert.accept();
		sleepInSecond(3);
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);

		// Switch qua Alert
		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);

		// Switch qua Alert
		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Cancel an Alert
		alert.dismiss();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");

		String textToSendkey = "Nghia Huynh";

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);

		// Switch qua Alert
		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// Nhập text
		alert.sendKeys(textToSendkey);

		// Accept an Alert
		alert.accept();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ textToSendkey);
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