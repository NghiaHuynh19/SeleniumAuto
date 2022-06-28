package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_14_Windows_Tab {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Basic_Form() {
		// Đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/");

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		// Switch qua trang B
		switchToWindowByTitle("Google");

		// Drive đang ở trang B
		driver.findElement(By.name("q")).sendKeys("Selenium");

		// Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);

		// Switch sang trang C
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		driver. findElement(By.id("email")).sendKeys("nghiahuynh.hmn99@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789kk");

		// Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);

		// Switch sang trang D
		switchToWindowByID("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
	}

	@Test
	public void TC_02_TechPanda() {
		// Đang ở trang Mobile
		driver.get("http://live.techpanda.org/index.php/mobile.html");

		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(3);

		// Switch qua Window Compare
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());

		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		sleepInSecond(3);

		// Switch qua Mobile
		switchToWindowByTitle("Mobile");

		// Driver đang ở trang Compare
		driver.findElement(By.id("search")).sendKeys("Samsung Galaxy");
		sleepInSecond(5);


	}

	@Test
	public void TC_03_Cambirdge_Dictonary() {
		// Đang ở trang Home
		driver.get("https://dictionary.cambridge.org/vi/");

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);

		// Switch qua trang Login
		switchToWindowByTitle("Login");

		// Login
		driver.findElement(By.xpath("//input[@value='Log in']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(),"This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span")).getText(),"This field is required");

		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Automation000***");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();

		// Business nó tự close đi và nhảy về trang trước đó
		// Driver nó vẫn đang ở trang Login

		// Swtich qua trang Home
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(3);

		// Verify login thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(),"Automation FC");

	}

	// Nó chỉ dùng cho duy nhất 2 tab/ window
	// Kiểm tra trc rồi switch sau
	public void switchToWindowByID (String parentID) {
		// Lấy ra tất cả các ID của tab/window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id: allWindowIDs) {
			// Nếu như ID nào mà khác với ParentID
			if (!id.equals(parentID)) {
				// Switch vào
				driver.switchTo().window(id);
				break;
			}
		}
	}

	// Dùng được 2 cho hoặc nhiều hơn 2 tab/ window
	// Switch trc rồi kiểm tra sau
	public void switchToWindowByTitle(String expectedPageTitile) {
		// Lấy ra tất cả các ID của tab/ window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng trong vòng lặp để duyệt qua từng ID
		for (String id: allWindowIDs) {
			// Switch vào từng tab/ window trước
			driver.switchTo().window(id);

			// Lấy ra cái title của page đã switch vào
			String currentPageTitle = driver.getTitle();

			if (currentPageTitle.equals(expectedPageTitile	)) {
				// Thoát khỏi vòng lặp - ko duyệt tiếp nữa
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(String parentID) {
		// Lấy ra tất cả các ID của tab/ window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng trong vòng lặp để duyệt qua từng ID
		for (String id: allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);

				// Đóng tab đang active
				driver.close();

			}
		}
		// Vẫn còn lại parent ID
		driver.switchTo().window(parentID);
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