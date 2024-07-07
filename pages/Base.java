package pages;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public WebDriver driver;
	public WebDriverWait wait;

	public Base(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void writeTxt(By element, String txt) {

		driver.findElement(element).sendKeys(txt);
	}

	public String createMail() {

		String a = "per";
		String b = "@gmail.com";
		Random r = new Random();
		String c = String.valueOf(r.nextInt(3000));
		String mail = a + c + b;
		System.out.println(mail);
		return mail;
	}

	public String getAppname() {

		String a = "per";
		Random r = new Random();
		String c = String.valueOf(r.nextInt(3000));
		String appName = a + c;

		return appName;
	}

}
