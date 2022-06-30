package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_Upload_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// Img name
	String vietnam = "VietNam.jpg";
	String thailand = "Thailand.jpg";
	String indonesia = "Indonesia.jpg";

	// Img path
	String vietnamFilePath = projectPath + "/uploadFiles/" + vietnam;
	String thailanFilePath = projectPath + "/uploadFiles/" + thailand;
	String indonesiaFilePath = projectPath + "/uploadFiles/" + indonesia;
	

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
	public void TC_01() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
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