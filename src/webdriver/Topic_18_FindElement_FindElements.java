package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_18_FindElement_FindElements {
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

		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Đang apply 15s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_FindElement() {
		// - Tìm thấy duy nhất 1 element / node
		// Tìm thấy và  thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên ko thấy cần phải chờ hết timeout 15s
		driver.findElement(By.cssSelector("input#email"));

		// -Tìm thấy nhiều hơn 1 element / node
		// Nó sẽ thao tác với node đầu tiên
		// Ko quan tâm các node còn lại
		// Trong trường hợp các bạn bắt locator sai thì nó tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("nghiahuynh@gmail.com");

		// -Không tìm thấy element/ node nào
		// Có cơ chế tìm lại = nửa giây (0.5s) sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn dk - Pass
		// Nếu hết thời gian (15s) mà vẫn ko thấy element thì
		// + Đánh fail testcase + ko chạy step tiếp theo
		// + Throw  ra 1 ngoại lệ: NoSuchElementExeption
		driver.findElement(By.cssSelector("input[type='check']"));

	}

	@Test
	public void TC_02_FindElements() {
		// -Tìm thấy duy nhất 1 element / node
		// -Tìm thấy và lưu nó vào list
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 15s

		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		// -Tìm thấy nhiều hơn 1 element / node
		// Tìm thấy và lưu nó vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());

		// Không tìm thấy element/ node nào
		// Có cơ chế tìm lại = nửa giây (0.5s) sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn dk - Pass
		// Nếu hết thời gian (15s) mà vẫn ko thấy element thì
		// + Ko đánh fail testcase + vẫn chạy các step tiếp theo
		// + Trả về 1 list rỗng (empty) = 0
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}