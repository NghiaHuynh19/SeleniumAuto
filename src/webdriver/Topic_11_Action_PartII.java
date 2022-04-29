package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class Topic_11_Action_PartII {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name").toLowerCase();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver,10);

		// 1. Khởi tạo action lên
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		// Scroll đến element này
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
		sleepInSecond(3);

		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

		// Hover vào Paste
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);

		// Verify Paste được hover thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

		// Click vào Paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);

		// Acccept alert sau khi click vào Paste
		expliciWait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	@Test
	public void TC_03_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCirle = driver.findElement(By.id("draggable"));
		WebElement bigCirle = driver.findElement(By.id("droptarget"));

		action.dragAndDrop(smallCirle,bigCirle).perform();
		sleepInSecond(3);

		Assert.assertEquals(bigCirle.getText(), "You did great!");

		// Background
		Assert.assertEquals(Color.fromString(bigCirle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
	}

	@Test
	public void TC_04_Drag_And_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String squareA = "column-a";
		String squareB = "column-b";

		// Lấy được toàn bộ nội dung trong file này ra
		String dragDropHelperContent = getContentFile(projectPath + "\\dragAndDrop\\drag_and_drop_helper.js");

		dragDropHelperContent = dragDropHelperContent + "$(\"" + squareA + "\").simulateDragDrop({ dropTarget: \"" + squareB + "\"});";
		jsExecutor.executeScript(dragDropHelperContent);


	}

	@Test
	public void TC_05_Drag_And_Drop_HTML5_Xpath() {

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

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}