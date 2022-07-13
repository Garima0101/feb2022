package learnConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNg {

	WebDriver driver;
	String browser;
	String url;
	// By type Element List
	By username = By.xpath("//input[@id='username']");
	By password = By.xpath("//input[@id='password']");
	By DASHBOARD_HEADER_FIELD = By.xpath("//h2[contains(text(),'Dashboard ')]");
	By SIGNIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");

	// Test Data
	String UName = "techfiosdemo@gmail.com";
	String Pswd = "abc123";
	String dashoardHeader = "Dashboard1";

	@BeforeClass
	public void readconfig() {
		// InputStream //BufferReader //Sanner // FileReader
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void initial() {

//		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe\\");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void login() {

		driver.findElement(username).sendKeys(UName);
		driver.findElement(password).sendKeys(Pswd);
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashoardHeader, "Couldnot find");

	}

//	@AfterMethod
	public void teardown() {

		driver.close();
		driver.quit();
	}

}
