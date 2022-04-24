package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Action_Part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name").toLowerCase();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// 1. Khởi tạo action lên
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		WebElement yourAgeTextbox = driver.findElement(By.id("age"));

		// Hover chuột vào Textbox
		action.moveToElement(yourAgeTextbox).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");

		// 2. Gọi hàm cần dùng ra
		// 3. Gọi cái perform() cuối cùng

		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInSecond(3);

		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");

	}

	@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Khai báo tất cả 12 Element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		// 1 --> 4
		// Click and hold vào 1
		// Hover tới 4
		// Nhả chuột trái ra
		// Thực thi các câu lệnh
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(5);

		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(),4);
	}

	@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Khai báo và lưu trữ lại tất cả 12 Element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		Keys control;
		if (osName.contains("Windows") || osName.contains("Linux")){
			control = Keys.CONTROL;
		} else {
			control = Keys.COMMAND;
		}

		// 1 và 5 và 11
		// Nhắn phím Ctrl xuống (chưa nhả ra)
		// Click vào 1
		// Click vào 5
		// Click vào 11
		// Thực thi các câu lệnh
		// Nhả phím Ctrl
		action.keyDown(control).perform();
		action.click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(10)).perform();
		action.keyUp(control).perform();
		sleepInSecond(5);

		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(),3);
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