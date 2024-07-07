package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageMethods extends Base {

	public PageMethods(WebDriver driver) {
		super(driver);
	}

	String url = "http://coop.apps.symfonycasts.com/";
	String registerButtonxp = "//*[@class='nav navbar-nav navbar-right']/li/a[@href='/register']";
	String apiButtonxp = "//*[@class='nav navbar-nav navbar-right']/li[2]/a";
	String clientIdxp = "//table[@class='table table-striped table-bordered table-hover app-details-table']/tbody/tr[1]/td[2]";
	String clSecretxp = "//table[@class='table table-striped table-bordered table-hover app-details-table']/tbody/tr[2]/td[2]";
	String urltxtxp = "//pre[@class='alert-info']/code/a";

	public void getUrl() {

		driver.get(url);
	}

	public void registration() {

		driver.findElement(By.xpath(registerButtonxp)).click();
		writeTxt(By.id("form-email"), createMail());
		writeTxt(By.id("form-password"), "0222227225Sm.");
		writeTxt(By.id("form-address"), "mise ilica 5");
		writeTxt(By.id("form-first-name"), "pera");
		writeTxt(By.id("form-last-name"), "ilic");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

		WebElement header = driver.findElement(By.cssSelector("div.row:nth-child(1) > h3"));
		wait.until(ExpectedConditions.visibilityOf(header));
		if (header.getText().contains("pera")) {
			System.out.println("registration succsesfull");
		}
	}

	public String getUserID() {

		driver.findElement(By.xpath(apiButtonxp)).click();
		String userdata = driver.findElement(By.xpath("//p[@class='alert alert-info']")).getText();
		String userId = userdata.split("is: ")[1];
		return userId;
	}

	public void createApp() {

		driver.findElement(By.linkText("Your Applications")).click();
		driver.findElement(By.linkText("Create your Application")).click();
		writeTxt(By.id("app-name"), getAppname());
		writeTxt(By.id("app-redirect_uri"), "https://www.google.com/");
		driver.findElement(By.xpath("//input[@value='barn-unlock']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement appHeader = driver.findElement(By.xpath("//*[@class='page-header']/h1"));
		wait.until(ExpectedConditions.visibilityOf(appHeader));
		if (appHeader.getText().contains("per")) {
			System.out.println("The app has been created");
		}
	}

	public List<String> getAppData() {

		List<String> data = new ArrayList<>();
		data.add(driver.findElement(By.xpath(clientIdxp)).getText());
		data.add(driver.findElement(By.xpath(clSecretxp)).getText());
		data.add(driver.findElement(By.xpath(urltxtxp)).getAttribute("href"));
		return data;
	}

//unused
	public String redirectUrlAndGetToken(List<String> data) {

		String url = data.get(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
			}
		}
		driver.navigate().to(url);
		driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
		wait.until(ExpectedConditions.urlContains("code"));
		String urlWithCode = driver.getCurrentUrl();
		String code = urlWithCode.split("=")[1];
		return code;
	}

}
