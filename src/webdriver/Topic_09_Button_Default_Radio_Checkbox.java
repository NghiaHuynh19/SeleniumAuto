package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Default_Radio_Checkbox {
	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		
		
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	 public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("automationfc");
		sleepInSecond(1);
		
		// Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		// Verify background color = RED
		String loginButtonBackgroundColorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGB color = " + loginButtonBackgroundColorRgb);
		
		// Verify = RGB
		Assert.assertEquals(loginButtonBackgroundColorRgb, "rgb(201, 33, 39)");
		
		
		// Convert qua Hexa
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRgb).asHex();
		System.out.println("Hexa color = " + loginButtonBackgroundColorHexa);
		
		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		// Remove disabled attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButtonBy));
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='S??? ??i???n tho???i/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), 
							"Th??ng tin n??y kh??ng th??? ????? tr???ng");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='M???t kh???u']/following-sibling::div[@class='fhs-input-alert']")).getText(), 
							"Th??ng tin n??y kh??ng th??? ????? tr???ng");
		
		
	}

	@Test
	public void TC_02_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By oneDotEightPetrolRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By twoDotZeroPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By threeDotSixPetrolRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		// 1.8 petrol
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		
		driver.findElement(oneDotEightPetrolRadio).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isSelected());
		
		// 2.0 petrol
		driver.findElement(twoDotZeroPetrolRadio).click();
		sleepInSecond(2);
		// Deselected
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		// Selected
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isSelected());
		
		// Enable
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isEnabled());
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isEnabled());
		
		// 3.6 petrol
		// Disable = Read only
		Assert.assertFalse(driver.findElement(threeDotSixPetrolRadio).isEnabled());
		
	}

	@Test
	public void TC_03_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By towarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		By leatherCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		
		// Select
		checkToCheckbox(luggageCheckbox);
		checkToCheckbox(heatedCheckbox);
		
		
		// Selected
		Assert.assertTrue(isElementSelected(luggageCheckbox));
		Assert.assertTrue(isElementSelected(heatedCheckbox));
		Assert.assertTrue(isElementSelected(leatherCheckbox));
		
		// Disabled
		Assert.assertFalse(isElementEnabled(towarCheckbox));
		Assert.assertFalse(isElementEnabled(leatherCheckbox));
		
		// De -Select
		uncheckToCheckbox(luggageCheckbox);
		uncheckToCheckbox(heatedCheckbox);
				
		// De -Selected
		Assert.assertFalse(isElementSelected(luggageCheckbox));
		Assert.assertFalse(isElementSelected(heatedCheckbox));
		Assert.assertFalse(isElementSelected(towarCheckbox));
	}

	@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		System.out.println("Checkbox size ="  + checkboxes.size());
		
		// Action - Select
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInMiliSecond(500);
			}
			
		}
		
		//Verify - Selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		// Action - DeSelect
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInMiliSecond(500);
			}
			
		}

		// Verify - DeSelected
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
		}
		
	}

	@Test
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");

		// 1 element ph???i defiine t???i 2 locator
		// D??? b??? nh???m l???n: Team member h??? d??ng -> hi???u nh???m
		// B???o tr??: Code nhi???u
		By summerCheckboxInput = By.cssSelector("input[value='Summer']");

		// Case 1: D??ng th??? input
		// Selenium click(); -> ElementNotInteractableException
		// isSelected(); -> Work
		
		
		// Case 2: D??ng th??? span
		// Selenium click(); -> Work
		// isSelected(); -> Ko Work

		// Case 3: D??ng th??? span - click
		// D??ng th??? input - isSelected()

		// Case 4: D??ng th??? input
		// Javascript - click
		// sSelected() - verify

		clickByJavascript(summerCheckboxInput);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(summerCheckboxInput).isSelected());
	}
	
	@Test
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);

		Assert.assertTrue(isElementSelected(checkedCheckbox));
		Assert.assertTrue(isElementSelected(indeterminateCheckbox));

		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);

		Assert.assertFalse(isElementSelected(checkedCheckbox));
		Assert.assertFalse(isElementSelected(indeterminateCheckbox));



	}

	@Test
	public void TC_07_Custom_Radio(){
			driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

			By myselfRadio = By.xpath("//div[text()='????ng k?? b???n th??n']/preceding-sibling::div/input");
			By myfamilyRadio = By.xpath("//div[text()='????ng k?? cho ng?????i th??n']/preceding-sibling::div/input");

			clickByJavascript(myfamilyRadio);
			sleepInSecond(2);

			Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());

			clickByJavascript(myselfRadio);
			sleepInSecond(2);

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
			Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);





	}

	@Test
	public void TC_08_Custom_Radio_Radio_Google_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		By haiPhongCityRadio = By.xpath("//div[@aria-label='H???i Ph??ng']");
		By quangnamCityRadio = By.xpath("//div[@aria-label='Qu???ng Nam']");
		By quangbinhCityRadio = By.xpath("//div[@aria-label='Qu???ng B??nh']");

		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangnamCityRadio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangbinhCityRadio).getAttribute("aria-checked"), "false");

		driver.findElement(haiPhongCityRadio).click();
		sleepInSecond(2);

		driver.findElement(quangnamCityRadio).click();
		sleepInSecond(2);

		driver.findElement(quangbinhCityRadio).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangnamCityRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangbinhCityRadio).getAttribute("aria-checked"), "true");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label = 'H???i Ph??ng' and @aria-checked = 'true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label = 'Qu???ng Nam' and @aria-checked = 'true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label = 'Qu???ng B??nh' and @aria-checked = 'true']")).isDisplayed());
	}

    public void clickByJavascript(By by){

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));

    }


	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
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
	
	public void sleepInMiliSecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}