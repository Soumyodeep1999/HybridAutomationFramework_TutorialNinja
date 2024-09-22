package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.RegisterPage;
import pages.RegistrationSuccessPage;
import utils.Utility;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	HomePage homePage;
	RegistrationSuccessPage registrationSuccessPage;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplication();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterFromMyAccountDropMenu();
	}

	@Test
	public void registerWithMandatoryFields() throws InterruptedException {

		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(Utility.emailGeneratorWithDateAndTime());
		registerPage.enterTelePhone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("Password"));
		registerPage.enterConfirmPassword(prop.getProperty("Password"));
		registerPage.checkPrivacyPolicyCheckbox();
		registrationSuccessPage = registerPage.clickOnContinueButton();
		String actualMessage = registrationSuccessPage.accountCreationMessage();
		Assert.assertTrue(actualMessage.equals(prop.getProperty("SuccessfullAccountCreationMessage")));

	}

	@Test
	public void differentPasswordinConfirmPasswordField() {

		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(Utility.emailGeneratorWithDateAndTime());
		registerPage.enterTelePhone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("Password"));
		registerPage.enterConfirmPassword(prop.getProperty("InvalidConfirmPassword"));
		registerPage.checkPrivacyPolicyCheckbox();
		registrationSuccessPage = registerPage.clickOnContinueButton();

		String errorMessage = registerPage.confirmPasswordMismatchErrorMessage();
		Assert.assertTrue(errorMessage.equals(prop.getProperty("ConfirmPasswordMismatchMessage")));
	}

	@Test
	public void validateErrorMessagesOfMandatoryFields() {

		registerPage.clickOnContinueButton();
		Assert.assertTrue(
				registerPage.retriveMissingPrivacyPolicyError().contains(prop.getProperty("PrivacyPolicyError")));
		Assert.assertTrue(
				registerPage.retriveMissingFirstNameError().equals(prop.getProperty("FirstNameMissingError")));
		Assert.assertTrue(registerPage.retriveMissingLastNameError().equals(prop.getProperty("LastNameMissingError")));
		Assert.assertTrue(registerPage.retriveMissingEmailError().equals(prop.getProperty("EmailMissingError")));
		Assert.assertTrue(
				registerPage.retriveMissingTelephoneError().equals(prop.getProperty("PhoneNumberMissingError")));
		Assert.assertTrue(registerPage.retriveMissingPasswordError().equals(prop.getProperty("PasswordMissingError")));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
