package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {

	String geckoPath = "C:\\Users\\zikaz\\OneDrive\\Desktop\\projects\\symphonyCoopSelenium_RestAssured\\geckodriver.exe";
	public WebDriver driver;

	public FirefoxOptions setOptions() {

		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--headless");
		return option;
	}

	public void setup() {

		FirefoxOptions option = setOptions();
		System.setProperty("webdriver.gecko.driver", geckoPath);
		driver = new FirefoxDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void teardown() {

		driver.quit();
	}

}
