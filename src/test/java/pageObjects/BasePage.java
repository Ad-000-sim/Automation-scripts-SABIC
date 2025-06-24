package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait ww;
	Actions a;

	public BasePage(WebDriver driver) {
		this.driver=driver;
		this.ww= new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver,this);	
		a=new Actions(driver);

		
	}
}
