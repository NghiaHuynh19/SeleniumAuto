package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Upload_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// Img name
	String vietnam = "VietNam.jpg";
	String thailand = "Thailand.jpg";
	String indonesia = "Indonesia.jpg";

	// Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// Img path
	String vietnamFilePath = uploadFileFolderPath + vietnam;
	String thailanFilePath = uploadFileFolderPath + thailand;
	String indonesiaFilePath = uploadFileFolderPath + indonesia;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFile = By.cssSelector("input[type='file']");

		//Load file lên (Browse file)
		driver.findElement(uploadFile).sendKeys(vietnamFilePath);
		driver.findElement(uploadFile).sendKeys(thailanFilePath);
		driver.findElement(uploadFile).sendKeys(indonesiaFilePath);
		sleepInSecond(3);

		// Verify image load  lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ vietnam +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thailand +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ indonesia +"']")).isDisplayed());

		// Chưa thực hiện Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}

		// Verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ vietnam +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ thailand +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ indonesia +"']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFile = By.cssSelector("input[type='file']");

		//Load file lên (Browse file)
		driver.findElement(uploadFile).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);

		// Verify image load  lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ vietnam +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thailand +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ indonesia +"']")).isDisplayed());

		// Chưa thực hiện Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}

		// Verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ vietnam +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ thailand +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '"+ indonesia +"']")).isDisplayed());

	}

	@Test
		public void TC_03_Upload_One_File_Per_Time_Java_Robot() throws AWTException {
			driver.get("https://blueimp.github.io/jQuery-File-Upload/");


			  // Load file bằng Robot
		  	 // Giả lập hành vi COPY path của 1 file --> Java support
		  	// Giả lập hành vi PASTE vào và ENTER vào  Open File Dialog
		 driver.findElement(By.cssSelector("span.btn-success")).click();
		 sleepInSecond(3);

		 loadFileByRobot(vietnamFilePath);

		 // Verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ vietnam +"']")).isDisplayed());

		// Thực hiện Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ vietnam +"']")).isDisplayed());


	}

	public  void loadFileByRobot (String filepath) throws AWTException {
		StringSelection select = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);

		// Nhan xuong Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);

		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);

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