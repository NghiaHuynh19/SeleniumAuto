package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_20_StaticWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) { // MacOS
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else { // Windows
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		driver.manage().window().maximize();


		
	}
	// Case ko đủ thời gian
	@Test
	public void TC_01_Not_Enough() {
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		sleepInSecond(3);

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");


	}

	// Case đủ thời gian
	@Test
	public void TC_02_Enough() {
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		sleepInSecond(10);

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

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