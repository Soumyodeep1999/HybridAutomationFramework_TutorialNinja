package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver ;
	
	@FindBy(linkText="HP LP3065")
	private WebElement hpProduct ;
	@FindBy(xpath="//div[@id=\"content\"]/h2/following-sibling::p")
	private WebElement productNotFoundErrorMessage ;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	public boolean visibilityOfHpProduct() {
		return hpProduct.isDisplayed();
	}
	public String retriveProductNotFoundErrorMessage() {
		return productNotFoundErrorMessage.getText();
	}

}
