package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myAccount;
	@FindBy(linkText = "Login")
	private WebElement loginDropDownOption;
	@FindBy(linkText = "Register")
	private WebElement registerDropDownOption;
	@FindBy(xpath="//div[@id=\"search\"]/input")
	private WebElement searchTextBox ;
	@FindBy(xpath="//div/span/button")
	private WebElement searchButton ;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnMyAccount() {
		myAccount.click();
	}

	public LoginPage selectLoginFromMyAccountDropMenu() {
		loginDropDownOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage selectRegisterFromMyAccountDropMenu() {
		registerDropDownOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductInSearchBox(String productName) {
		searchTextBox.sendKeys(productName);
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	public SearchPage searchProduct(String productName) {
		enterProductInSearchBox(productName);
		clickOnSearchButton();
		return new SearchPage(driver);
	}

}
