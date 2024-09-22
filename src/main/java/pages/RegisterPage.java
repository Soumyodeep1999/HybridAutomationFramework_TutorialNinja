package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Elements

	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@type=\"checkbox\"]")
	private WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueButton;

	@FindBy(xpath = "//*[@id=\"content\"]/form/fieldset[2]/div[2]/div/div")
	private WebElement confirmPasswordMismatchError;

	@FindBy(xpath = "//div[contains(@class,\"alert-dismissible\")]")
	private WebElement missingPrivacyPolicyError;

	@FindBy(xpath = "//input[@id=\"input-firstname\"]/following-sibling::div")
	private WebElement missingFirstNameError;

	@FindBy(xpath = "//input[@id=\"input-lastname\"]/following-sibling::div")
	private WebElement missingLastNameError;

	@FindBy(xpath = "//input[@id=\"input-email\"]/following-sibling::div")
	private WebElement missingEmailError;

	@FindBy(xpath = "//input[@id=\"input-telephone\"]/following-sibling::div")
	private WebElement missingTelephoneError;

	@FindBy(xpath = "//input[@id=\"input-password\"]/following-sibling::div")
	private WebElement missingPasswordError;

	// Actions
	public void enterFirstName(String fN) {
		firstName.sendKeys(fN);
	}

	public void enterLastName(String lN) {
		lastName.sendKeys(lN);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterTelePhone(String phone) {
		telephone.sendKeys(phone);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void checkPrivacyPolicyCheckbox() {
		privacyPolicyCheckbox.click();
	}

	public RegistrationSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new RegistrationSuccessPage(driver);
	}

	public String confirmPasswordMismatchErrorMessage() {
		return confirmPasswordMismatchError.getText();
	}

	public String retriveMissingPrivacyPolicyError() {
		return missingPrivacyPolicyError.getText();

	}

	public String retriveMissingFirstNameError() {
		return missingFirstNameError.getText();

	}

	public String retriveMissingLastNameError() {
		return missingLastNameError.getText();

	}

	public String retriveMissingEmailError() {
		return missingEmailError.getText();
	}

	public String retriveMissingTelephoneError() {
		return missingTelephoneError.getText();
	}

	public String retriveMissingPasswordError() {
		return missingPasswordError.getText();
	}

}
