package basic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		//Primitive Type
		//8 loại
		//char - kí tự - 16
		char c = 'A';
		System.out.println(c);
		//byte - số nguyên - 8
		byte bNumber = 15;
		System.out.println(bNumber);
		//short - số nguyên -16
		short sNumber = 625;
		System.out.println(sNumber);
		//int - số nguyên -32
		int iNumber = 20000000;
		System.out.println(iNumber);
		//long - số nguyên - 64
		long number = 954235676;
		System.out.println(number);
		//float - số thực- 32
		float fNumber = 9.5f;
		System.out.println(fNumber);
		//double - số thực- 64
		double dNumber = 9.5d;
		System.out.println(dNumber);
		//boolean - logic- 1 (true/false)
		boolean mariedStatus = true;
		System.out.println(mariedStatus);
		//Reference Type
		//Object
		Object o = new Object();
		//Array
		String[] address = {"Ha Noi", "Sai Gon", "Da Nang"};
		Integer[] phone = {037, 912};
		long a[] = {10000L, 50000L};
		//Class
		Topic_02_Data_Type topic =new Topic_02_Data_Type();
		//Interface
		WebDriver driver = null;
		//Collection: List
		List <String> addresses = new ArrayList<String>();
		//String- Chuỗi kí tự
		String name = "Automation !@#456789";
		String cityname = new String("Ho Chi Minh");
		
		WebElement emailTextbox = driver.findElement(By.cssSelector(""));
		
		List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
		
	}

}
