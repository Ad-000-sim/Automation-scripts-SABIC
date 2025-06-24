package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashBoardPage extends BasePage {


	public DashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	By ProfileIcon=By.id("profileDropdown"); 

	By profileLink=By.xpath("//span[.='Profile']");
	By LogoutLink=By.xpath("//span[.='Sign out']");
	By LogoutOpt=By.xpath("//a[.='Sign out']");
	
	By tenants=By.xpath("//span[.='Tenants']");
	By AccessTenants=By.xpath("//i[contains(@class,'fas fa-user text-secondary')]");
	
	By Userslink=By.xpath("//span[text()='Users']");
	
	
	public void clickProfilerIcon() {
		driver.findElement(ProfileIcon).click();
	    
	}
	public void clickProfileLink() {
		driver.findElement(profileLink).click();
	}
	public void clickLogoutLink() {
		driver.findElement(LogoutLink).click();
	}
	
	public void clickONLogoutInPopUp() {
		driver.findElement(LogoutOpt).click();
	}
	
    public void ClickOnUsersLink() {
    	driver.findElement(Userslink).click();
    }
    
    public void ClickonAccessTenants() {
    	
    	
    	WebElement AccTenLink = ww.until(ExpectedConditions.elementToBeClickable(AccessTenants));
    	AccTenLink.click();
    	//driver.findElement(AccessTenants).click();
    }
	
    public void clickonTenants() {
		//driver.findElement(tenants).click();
    	WebElement TenLink = ww.until(ExpectedConditions.elementToBeClickable(tenants));
    	TenLink.click();
	}

	
	

}
