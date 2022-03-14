package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		// Các hàm (function/ method)/ câu lệnh (command):
		// Tương tác với Browser
		//driver
		
		//Tương tác vs Element
		//Nếu như Element chỉ dùng duy nhất 1 lần thì ko cần khai báo biến
		//driver.findElement(By.name("login")).click();
		
		//Nếu như Element có dùng từ 2 lần trở lên thì nên khai báo biến để tái sử dụng
		//driver.findElement(By.cssSelector("#email")).clear();
		//driver.findElement(By.cssSelector("#email")).sendKeys("huynhminhnghia.hmn99@gmail.com");
		/*WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("huynhminhnghia.hmn99@gmail.com");
		emailTextbox.getCssValue("background-color");*/
		
		
		//Khai báo biến
		//String homePageUrl = driver.getCurrentUrl();
		
		WebElement element = driver.findElement(By.xpath(""));
		
		//Xóa dữ liệu trong textbox/ textarea/ editable dropdwon (cho phép edit/ nhập liệu)
		element.clear();
		
		//Nhập dữ liệu vào trong textbox/ textarea/ editable dropdown (cho phép edit/ nhập liệu)
		element.sendKeys("");
		
		//Click button/ radio button/ checkbox/ link/ image/...
		element.click();
		
		/*driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Chạy được nhưng nó ko phổ biến 
		driver.findElement(By.xpath("//div[@class='footer']")).findElement(By.xpath("//a[@title='My Account']")).click();*/
		
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		
		// Lấy ra giá trị của attribute
		element.getAttribute("");
		
		//Thường dùng để test GUI: front/ color/ size/ location/ position/...
		// Đều lấy ra các thuộc tính của Css được
		element.getCssValue("");
		
		element.getLocation();
		element.getRect();
		Dimension elementSize = element.getSize();
		//Chụp hình lại của element lại lưu bằng format nào đó
		String base64Image = element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		element.getTagName();
		
		//Lấy ra text của element
		element.getText();
		//Lấy ra tên thẻ (html)
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		String elementTagname = element.getTagName(); 
		//Kiểm tra 1 element có hiển thị hay ko - tất cả các loại element
		//Người dùng có thể nhìn thấy được
		
		//Mong đợi nó hiển thị
		Assert.assertTrue(element.isDisplayed());
		
		//Mong đợi nó không hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		//Kiểm tra 1 element có thể thao tác được hay không
		//Read-only hoặc disable element
		
		//Mong đợi nó thao tác được (enable)
		Assert.assertTrue(element.isEnabled());
		//Mong đợi nó bị disable/ read-only/ ko thao tác được
		Assert.assertFalse(element.isEnabled());
		
		//Kiểm tra 1 element đã được hay chưa
		//Radio button/ Checkbox/ Dropdown
		element.isSelected();
		
		//Mong đợi nó đã được chọn rồi
		Assert.assertTrue(element.isSelected());
		
		//Mong đợi nó chưa chọn/ bỏ chọn đi 
		Assert.assertFalse(element.isSelected());
		
		//Tương ứng với hành động là nhấn phím ENTER trên bàn phím
		// Chỉ dùng với element là form hoặc nằm trong form
		element.submit();
		
	}

	@Test
	public void TC_02() {
		
		
		
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