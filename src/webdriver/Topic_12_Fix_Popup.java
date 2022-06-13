package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_Fix_Popup {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_NgoaiNgu24h_By() {
		driver.get("https://ngoaingu24h.vn/");

		By loginPopupBy = By.cssSelector("div#modal-login-v1");

		// Verify login popup is not displayed
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());

		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);

		// Verify login popup is not displayed
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());

		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");

		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);

		// Verify login popup is not displayed
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());

	}

	@Test
	public void TC_02_NgoaiNgu24h_WebElement() {
		driver.get("https://ngoaingu24h.vn/");

		WebElement loginPopup = driver.findElement(By.cssSelector("div#modal-login-v1"));

		// Verify login popup is not displayed
		Assert.assertFalse(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);

		// Verify login popup is not displayed
		Assert.assertTrue(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");

		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);

		// Verify login popup is not displayed
		Assert.assertFalse(loginPopup.isDisplayed());
	}

	/*@Test
	public void TC_03_JTExpress() {
		driver.get("https://jtexpress.vn/");

		/*By homeSliderPopup = By.cssSelector("div#home-modal-slider");

		// Verify login popup is not displayed
		Assert.assertTrue(driver.findElement(homeSliderPopup).isDisplayed());

		driver.findElement(By.cssSelector("button.close")).click();
		sleepInSecond(2);

		// Verify login popup is not displayed
		Assert.assertFalse(driver.findElement(homeSliderPopup).isDisplayed());

		String billCode = "840000598444";

		driver.findElement(By.name("billcodes")).sendKeys("840000598444");
		driver.findElement(By.xpath("//form[@id ='formTrack' ] //button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(3);

		WebElement scrollbillCode = driver.findElement(By.xpath("//h5"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",scrollbillCode );
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//h5")).getText().contains(billCode));



	}*/
	@Test
	public void TC_03_Fix_In_DOM() {
		driver.get("https://kyna.vn/");

		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));

		// Undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
		
		// Click
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);

		// Displayed
		Assert.assertTrue(loginPopup.isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("minhnghia@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("12345678kk@");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();

		// Undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
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