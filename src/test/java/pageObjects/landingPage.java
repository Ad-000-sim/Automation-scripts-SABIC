package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class landingPage extends BasePage {

	
	public landingPage(WebDriver driver) {
		super(driver);
	}
	
	By submitBtnlp=By.xpath("//button[.='Sign in']");
	
	public void clickOnSubmitButtonLandingPage() {
		ww.until(ExpectedConditions.elementToBeClickable(submitBtnlp)).click();
	}

}
