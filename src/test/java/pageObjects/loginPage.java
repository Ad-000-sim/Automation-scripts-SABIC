package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;

public class loginPage extends BasePage {
	protected ConfigReader config; 
	WebElement btn;
	

	public loginPage(WebDriver driver) {
		super(driver);
		
	}
	
	By username=By.xpath("//input[@id='email']");
	By password=By.xpath("//input[@id='password']");
	By loginBtn=By.xpath("//button[@class='btn btn-primary']");
	By img=By.xpath("//a[@class='sidebar-brand']");
	By ErrMsg=By.xpath("//strong[.='Wrong email OR password.']");
	By loginError=By.xpath("//strong[.='Wrong email OR password.']");
	
	public void enterEmail(String email) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(email);
		
	}
	
	public void enterPassword(String pwd) {
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickonSubmit() {
	 WebElement btn = driver.findElement(loginBtn);
	 btn.click();
	 
	}
	
	
	public boolean isLoginErrorDisplayed() {
	    try {
	        return driver.findElement(By.xpath("loginError")).isDisplayed(); // Update locator
	    } catch (Exception e) {
	        return false;
	    }
	}
 
//	public boolean verifyImage() {
//	    try {
//	        return driver.findElement(By.id("img")).isDisplayed(); // Update locator
//	    } catch (Exception e) {
//	        return false;
//	    }
//	}
//	
	

	
	
	
	

}
