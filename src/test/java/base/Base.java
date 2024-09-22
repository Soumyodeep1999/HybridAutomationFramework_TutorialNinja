package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Utility;

public class Base {

	WebDriver driver;
	public Properties prop;

	public WebDriver initializeBrowserAndOpenApplication() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("Url"));
		return driver;

	}

	// Constructor of Base class with load prop func
	public Base() {

		prop = new Properties();

		File configProp = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\" + "config.properties");
		try {
			FileInputStream fis_registerProp = new FileInputStream(configProp);
			prop.load(fis_registerProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		File registerProp = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\" + "registerPage.properties");
		try {
			FileInputStream fis_registerProp = new FileInputStream(registerProp);
			prop.load(fis_registerProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		File searchProp = new File(System.getProperty("user.dir")+"\\src\\main\\java\\properties\\" + "search.properties");
		try {
			FileInputStream fis_searchProp = new FileInputStream(searchProp);
			prop.load(fis_searchProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		

	}

}
