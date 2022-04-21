package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_PartI {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
		
	}

	@Test
	public void TC_01_Browser() {
		// Các hàm/method để thao tác  vs Browser là thông qua biến driver
		
		//** Thường xuyên dùng
		//* Ít dùng
		// Mở ra 1 URl
		driver.get("https://ban.sendo.vn/"); //**
		
		driver.get("https://identity.transwap.dev/Account/Login?ReturnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dluminor-e6226036-c85e-45c8-8ec4-11c76c584dc0%26redirect_uri%3Dhttps%253A%252F%252Fapp.transwap.dev%252Fauthentication%252Fcallback%26response_type%3Did_token%2520token%26scope%3Dopenid%2520profile%2520Luminor-api%26state%3Decd1cbf402b6498fa7a7b940be8e9d43%26nonce%3D045257f8d6ab49cc928a2137556e7f21");
		
		//Đóng Browser với 1 tab và đóng tab hiện tại nếu có nhiều tab
		//WebDriver API - Windows/Tabs
		driver.close();//**
		//Đóng Browser ko quan tâm bao nhiêu tab
		driver.quit();//**
		
		//Tìm 1 element trên page
		//Trả về data type là WebElement
		WebElement emailTextbox = driver.findElement(By.id("email"));//**
		
		//Tìm nhiều hơn 1 element trên page
		//Trả về Data type là List<WebElement>
		List<WebElement> links =driver.findElements(By.xpath("/a"));//**
		
		//Trả về URL của page hiện tại
		String homepageUrl= driver.getCurrentUrl();//*
		//System.out.println();
		//Verify tuyệt đối  
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");
		
		//Lấy ra source code của trang hiện tại (HTML/CSS/JS/JQuery/...)
		//Verify tương đối 1 giá trị nào đó trong trang
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Welcome to our store"));
		Assert.assertTrue(homePageSource.contains("Online shopping is the process consumers go through to purchase products or services over the Internet."));
		
		//Lấy ra/ Trả về title của page hiện tại 
		String homePageTitle = driver.getTitle();//*
		Assert.assertEquals(homePageTitle, "nopCommerce demo store");
		
		//WebDriver API- Windows/Tab
		//Trả về 1 ID của tab hiện tại (1)
		String signUPTabID = driver.getWindowHandle();//*
		// Trả về ID tất cả các tab đang có (n)
		Set<String>allTabID = driver.getWindowHandles();//*
		
		// Xử lý cookie (Framework)
		driver.manage().getCookies();
		
		// Lấy log của browser ra (Framework)
		//driver.manage().logs()
		
		
		//driver.getPageSource();
		
		//Time findElement/ findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//**
		
		//Time page được load xong
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		// Time để 1 đoạn async script được thực thi thành công (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		//Full screen browser
		driver.manage().window().fullscreen();
		// End user maximize
		driver.manage().window().maximize();//*
		
		Point browserPosition = driver.manage().window().getPosition();
		
		//Set vị trí của browser tại điểm 0x250
		driver.manage().window().setPosition(new Point(0, 250));
		
		// Lấy ra chiều rộng/ chiều cao của browser
		Dimension browserSize = driver.manage().window().getSize();
		
		//Set browser mở kích thước  nào
		//Test Responsive
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		//Quay lại trang trước đó
		driver.navigate().back();
		
		//Chuyển tới trang trước đó
		driver.navigate().forward();
		
		//Tải lại trang
		driver.navigate().refresh();
		
		driver.navigate().to("https://ban.sendo.vn/");
		
		//WebDriver API - Alert/ Authencation Alert
		driver.switchTo().alert();//**
		//WebDriver API - Frame/ Iframe
		driver.switchTo().frame(1);//**
		//WebDriver API - Windows/ Tabs
		driver.switchTo().window("");//**
		
		
		
		//Verify dữ liệu này đúng như mong đợi
		//Assert.assertEquals(homepageUrl, "https://www.nopcommerce.com/en/demo");
		
		
		
		// Ko nên lưu thành 1 biến - tương tác trực tiếp
		/*
		 * WebElement emailTextbox = driver.findElement(By.id("email"));
		 * emailTextbox.clear(); emailTextbox.sendKeys("");
		 * emailTextbox.getAttribute("value");
		 */
		/*
		 * driver.findElement(By.id("email")).clear();
		 * driver.findElement(By.id("email")).sendKeys("");
		 * driver.findElement(By.id("email")).getAttribute("value");
		 */
		
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