package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Utility;

public class LoginTest extends Base {

	public LoginTest() {
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplication();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginFromMyAccountDropMenu();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "ValidCred")
	public Object[][] supplyTestData() {

		return Utility.getDataFromExcel("Login");

	}

	@Test(dataProvider = "ValidCred")
	public void verifyLoginWithValidCredential(String email, String password) {

		accountPage = new AccountPage(driver);
		accountPage = loginPage.login(email, password);

		Assert.assertTrue(accountPage.visibilityStatusOfEditYourAccountInformation());

	}

	@Test
	public void verifyLoginWithInvalidEmail() {

		loginPage.login(Utility.emailGeneratorWithDateAndTime(), prop.getProperty("Pwd"));
		Assert.assertEquals(loginPage.retriveInvalidEmailAndPasswordMessage(),
				prop.getProperty("EmailPasswordMismatchErrorMessage"));

	}
}
