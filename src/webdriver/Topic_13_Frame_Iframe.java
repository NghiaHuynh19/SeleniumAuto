package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Kyna() {
		// A
		driver.get("https://kyna.vn/");

		// A --> B
		// Switch vào frame/ iframe trước rồi mới thao tác lên element thuộc frame/ iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

		// B
		// Verify số lượng like trên Facebook fanpage 167K
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),"167K lượt thích");

		// B --> A
		driver.switchTo().defaultContent();
		// A --> C
		// Switch vào iframe chat trước
		driver.switchTo().frame("cs_chat_iframe");

		// C
		// Click vào chat để bật popup lên
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();

		// C
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Aubrey Huynh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0378912999");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Iframe");

		// C --> A
		driver.switchTo().defaultContent();

		String keyword = "Excel";
		// A
		// Nhập và search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();

		// Verify course name chứa từ khóa vừa nhập
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course: courseName){
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}

	@Test
	public void TC_02_Blog() {
		// A
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");

		// A --> B (Youtube)
		driver.switchTo().frame("iframe.youtube-player");
		// B

	}

	@Test
	public void TC_03_HDFC() {
		//A
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		// A --> B
		driver.switchTo().frame("login_page");

		//B
		driver.findElement(By.name("fldLoginUserId")).sendKeys("aubereyhuynh");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);

		// B - Password textbox is displayed
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
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