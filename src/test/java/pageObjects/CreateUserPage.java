package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateUserPage extends BasePage{
	
	

	public CreateUserPage(WebDriver driver) {
		super(driver);
	}
	
	By AdduserBtn=By.xpath("//a[@class='btn btn-sm btn-primary btn-icon-text mb-2 mb-md-0']");
	By FirstNameTF=By.xpath("//input[@type='text' and @name='first_name']");
	By lastnameTF=By.xpath("//input[@type='text' and @name='last_name']");
	By emailTF=By.xpath("//input[@type='email' or @name='email']");
	By phoneTf=By.xpath("//input[@type='number' or @name='mobile_number']");
	
	By Countrydd=By.xpath("//span[@id='select2-users_location-container' or text()='Select Country']");
	By countryOpt=By.xpath("//li[.='IND - INDIA']");
	//By CountryseFld=By.xpath("//input[@class='select2-search__field' or aria-controls='select2-users_location-results']");//use sendKeys
	
	By citydd=By.xpath("//span[@id='select2-city-container' or text()='Select City']");
	By cityOpt=By.xpath("//li[text()='Bengaluru']");
	//By cityField=By.xpath("//input[@type='search']"); //use sendKeys to fetch the exact city name
	
	By Projectdd=By.xpath("//span[@id='select2-project-container' or text()='Select Project']");
	By ProjOpt=By.xpath("//li[contains(., 'HDPE')]");
	//By ProjseF=By.xpath("//input[@class='select2-search__field']"); //use sendKeys to fetch the exact workspace name
	
	By UserRoleDD=By.xpath("//span[@id='select2-user_role-container' or text()='Select User Role']");
	By UserRoleOpt=By.xpath("//li[.='Admin']");
	//By userRoleSeFld=By.xpath("//input[@class='select2-search__field']"); //use sendkeys to fetch data
	
    By submitBtn=By.id("submit_button_id");
    
    By Passwordlnk=By.xpath("(//i[@class='fas fa-unlock text-secondary'])[1]");
    
    By Pwd=By.name("password");
    By cnfPwd=By.name("password_confirmation");
    By submitBtn2=By.xpath("//button[.='Submit']");
    
    
    public void clickOnAddUserBtn() {
    	//driver.findElement(AdduserBtn).click();
        ww.until(ExpectedConditions.elementToBeClickable(AdduserBtn)).click();
    }

    public void EnterFirstName(String fn) {
    	//driver.findElement(FirstNameTF).sendKeys(fn);
        ww.until(ExpectedConditions.visibilityOfElementLocated(FirstNameTF)).sendKeys(fn);
    	
    }

    public void EnterLastName(String ln) {
    	//driver.findElement(lastnameTF).sendKeys(ln);
        ww.until(ExpectedConditions.visibilityOfElementLocated(lastnameTF)).sendKeys(ln);
    	
    }

    public void EnterEmail(String em) {
    	//driver.findElement(emailTF).sendKeys(em);

        ww.until(ExpectedConditions.visibilityOfElementLocated(emailTF)).sendKeys(em);
    }

    public void EnterPhoneNumber(String ph) {
    	//driver.findElement(phoneTf).sendKeys(ph);

        ww.until(ExpectedConditions.visibilityOfElementLocated(phoneTf)).sendKeys(ph);
    }
    public void selectCountry() {
    	
    	WebElement CountryseFld = ww.until(ExpectedConditions.elementToBeClickable(Countrydd));
    	CountryseFld.click();
    	driver.findElement(countryOpt).click();
//    	CountryseFld.sendKeys(countryName);
//    	CountryseFld.sendKeys(Keys.ENTER);

    }
 
    public void selectCity() {
    	WebElement cityField = ww.until(ExpectedConditions.elementToBeClickable(citydd));
    	cityField.click();
    	driver.findElement(cityOpt).click();
//    	cityField.sendKeys(cityName);
//    	cityField.sendKeys(Keys.ENTER);
    }

    
    public void selectDefaultProject() {
    	WebElement ProjseF = ww.until(ExpectedConditions.elementToBeClickable(Projectdd));
    	ProjseF.click();
    	driver.findElement(ProjOpt).click();
//    	ProjseF.sendKeys(projectName);
//    	ProjseF.sendKeys(Keys.ENTER);
    }

    
    public void selectUserRole() {
    	WebElement userRoleSeFld = ww.until(ExpectedConditions.elementToBeClickable(UserRoleDD));
    	userRoleSeFld.click();
    	driver.findElement(UserRoleOpt).click();
//    	userRoleSeFld.sendKeys(roleName);
//    	userRoleSeFld.sendKeys(Keys.ENTER);
    }

    public void clickOnSubmitButton() {
    	WebElement submitButton = ww.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_button_id")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    	ww.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    
    public void clickOnSetNewPassword() {
    	WebElement click = ww.until(ExpectedConditions.elementToBeClickable(Passwordlnk));
    	click.click();
    }
    
    public void enterPassword(String password) {
        WebElement passwordField = ww.until(ExpectedConditions.elementToBeClickable(Pwd));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordField = ww.until(ExpectedConditions.elementToBeClickable(cnfPwd));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickOnSecondSubmitButton() {
        WebElement submitButton = ww.until(ExpectedConditions.elementToBeClickable(submitBtn2));
        submitButton.click();
    }










    
	
	
	
	
	
	

}
