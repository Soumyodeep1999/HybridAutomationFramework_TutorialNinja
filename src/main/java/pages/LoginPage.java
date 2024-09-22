package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Elements
	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value=\"Login\"]")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement invalidEmailAndPasswordMessage;

	// Actions
	public void enterEmailAddress(String email) {
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}

	public AccountPage login(String email, String password) {
		enterEmailAddress(email);
		enterPassword(password);
		return clickOnLoginButton();
	}

	public String retriveInvalidEmailAndPasswordMessage() {
		return invalidEmailAndPasswordMessage.getText();
	}
}
