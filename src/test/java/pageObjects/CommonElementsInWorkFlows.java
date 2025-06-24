package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Description;

public class CommonElementsInWorkFlows extends BasePage{

	public CommonElementsInWorkFlows(WebDriver driver) {
		super(driver);
	}
	By ReportName=By.id("prediction-profile-name");
	By DatasetDD=By.xpath("//span[text()='Select dataset']");   //commom ele
	By DatasetType=By.xpath("//li[normalize-space()='Cycoloy FR Pmml']");  
	
	
	
	By NextButton=By.xpath("//a[@href='#next']"); //common for all 3 tabs in pp workflow
	By PreviousButton=By.xpath("//a[normalize-space()='Previous']"); //common for 2 tabs in pp, rs workflows
	By PropertiesToggleBtn=By.xpath("//label[contains(@for,'customSwitch')]"); //multiple ele's common for all 3 workspaces
	
	
	By Categorydd=By.xpath("//span[@id='select2-gcategory-container']");
	
	
	By SelectIngredientdd=By.xpath("//span[@id='select2-product-container']");
	By IngredientNames=By.xpath("//li[@role='option']");  //multiple Ingred
	
	By addIngButton=By.xpath("//i[@class='fas fa-plus text-secondary']");
	
	By GenerateButton=By.xpath("//a[normalize-space()='Generate Report']");
	
	By GeneReport=By.xpath("//tbody/tr[1]");
	By success=By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Success']");
	By Fail= By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Failed']");
	By Processing=By.xpath("//i[@aria-label='Processing']");
	By RefreshIcon=By.xpath("//button[@id='refresh_spin']");
	
	
	public void EnterReportName(String rname) {
		WebElement inputField = ww.until(ExpectedConditions.visibilityOfElementLocated(ReportName));
	    inputField.clear(); // Optional: clear before entering text
	    inputField.sendKeys(rname);
	}
	
	
	
	public void clickOnDatasetDD() throws InterruptedException {
		WebElement dd = ww.until(ExpectedConditions.elementToBeClickable(DatasetDD));
	    dd.click();

	    WebElement typeOption = ww.until(ExpectedConditions.elementToBeClickable(DatasetType));
	    typeOption.click();
	    Thread.sleep(5000);
	}
	
	public void clickonNextButton() {
		 WebElement element = driver.findElement(NextButton);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		    ww.until(ExpectedConditions.elementToBeClickable(element));

		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	@Description("properties tab")
	public void TurnonPropToggle() throws InterruptedException {
		
		List<WebElement> togBtns = driver.findElements(PropertiesToggleBtn);

		for (WebElement toggle : togBtns) {
//		    ww.until(ExpectedConditions.elementToBeClickable(toggle)).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
		}
		Thread.sleep(2000);
	}



	
	
	
	
	

}
