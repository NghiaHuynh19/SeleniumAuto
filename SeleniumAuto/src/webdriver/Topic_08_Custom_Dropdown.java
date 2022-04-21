package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		explicitWait = new WebDriverWait(driver,30 );
		
		jsExecutor = (JavascriptExecutor) driver;
		
		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuerry() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "5");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "15");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "3");



	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
		
	}

	@Test
	public void TC_03_React() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
	}

	@Test
	public void TC_04_Angular_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Thành phố Hồ Chí Minh");
		//1: Text nó ko nằm ở bên HTML
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");

		//2
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		
		//3
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Thành phố Hồ Chí Minh");

		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Quận Bình Tân");

		selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Phường Bình Trị Đông A");

	}
	
	@Test
	public void TC_05_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		enterToCustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']","div[role='option']>span.ng-option-label","Thành phố Hồ Chí Minh");
		enterToCustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']","div[role='option']>span.ng-option-label","Quận Bình Tân");
		enterToCustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']","div[role='option']>span.ng-option-label","Phường Bình Trị Đông A");
	}

	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem){
		// Step 1: Click vào 1 element cho nó xổ hết ra các item
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);

		// Step 2: Chờ cho các item load hết ra thành công
		//  Lưu ý 1: Locator chứa hết tất cả các item
		//  Lưu ý 2: Locator phải đến node cuối cùng chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Step 3: Tìm item càn chọn

		// Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng item getText của item ra
		for (WebElement item : allItems){
			String	actualText = item.getText();
			System.out.println("Actucal Text = " + actualText);
			
			// Nếu text = text mình mong muốn (item cần click vào)
			if (actualText.equals(expectedTextItem)){

				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới element tìm tiếp
				// B2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến item đó

				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);

				// Step 4: Click vào item đó
				item.click();
				sleepInSecond(2);

				//Thoát khỏi vòng lập ko có kiểm tra element tiếp theo nữa
				break;
			}
		}
	}

	public void enterToCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem){
		// Step 1: Phải lấy đến thẻ input (textbox) để sendkey vào
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
		sleepInSecond(2);

		// Step 2: Chờ cho các item load hết ra thành công
		//  Lưu ý 1: Locator chứa hết tất cả các item
		//  Lưu ý 2: Locator phải đến node cuối cùng chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Step 3: Tìm item càn chọn

		// Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng item getText của item ra
		for (WebElement item : allItems){
			String	actualText = item.getText();
			System.out.println("Actucal Text = " + actualText);
			
			// Nếu text = text mình mong muốn (item cần click vào)
			if (actualText.equals(expectedTextItem)){

				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới element tìm tiếp
				// B2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến item đó

				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);

				// Step 4: Click vào item đó
				item.click();
				sleepInSecond(2);

				//Thoát khỏi vòng lập ko có kiểm tra element tiếp theo nữa
				break;
			}
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
}