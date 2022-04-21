package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String authenChorme = projectPath + "\\autoITScripts\\authen_chrome.exe";
	String authenFirefox = projectPath + "\\autoITScripts\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_00_Login_Empty_Data(){
		driver.get("https://demo.guru99.com/V4/index.php");

		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);

		// Chờ cho Alert xuất hiện + switch qua luôn
		alert= expliciWait.until(ExpectedConditions.alertIsPresent());

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

	@Test
	public void TC_04_Authentication_Alert_I(){
		String username = "admin";
		String password = "admin";
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}

	@Test
	public void TC_04_Authentication_Alert_II() {
		String username = "admin";
		String password = "admin";

		driver.get("http://the-internet.herokuapp.com/");

		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getAuthenticateLink(basicAuthenLink, username, password));
		driver.get(basicAuthenLink);
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	@Test
	public void TC_04_Authentication_Alert_III() throws IOException {
		String username = "admin";
		String password = "admin";

		driver.get("http://the-internet.herokuapp.com/");

		// Script sẽ chạy trước để chờ Authen alert bật lên sau

		if (driver.toString().contains("Firefox")){
			Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
		} else {
			Runtime.getRuntime().exec(new String[] {authenChorme, username, password});
		}

		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		sleepInSecond(8);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());


	}

	public String getAuthenticateLink(String url, String username, String password){
		String[] links = url.split("//");
		url = links[0] + "//" + username + ":" + password + "@" + links[1];
		return url;
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