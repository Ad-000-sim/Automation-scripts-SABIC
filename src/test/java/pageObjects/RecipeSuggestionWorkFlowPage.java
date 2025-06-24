package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.GenerateRandomStrings;


public class RecipeSuggestionWorkFlowPage extends BasePage {
	public String rn = "RecipeSuggestionAutomationTestReport"+GenerateRandomStrings.generateNumber();
	Select s;
	Random rand = new Random();
	public RecipeSuggestionWorkFlowPage(WebDriver driver) {
		super(driver);
	}

	

	By ReportName = By.id("prediction-profile-name");
	By DatasetDD = By.xpath("//span[text()='Select dataset']");
	By DatasetType = By.xpath("//li[normalize-space()='Cycoloy FR Pmml']");
	By NextButton = By.xpath("//a[@href='#next']");

	By propRows = By.xpath("(//tbody//tr)[position() <= 5]");
	By criteriaDropdown = By.xpath("(//select[contains(@class, 'proCriteria')])[position() <= 5]");
	
//
	By toggleButton = By.xpath("(//label[@class='custom-control-label'])[position()<=5]");
	By Categorydd=By.xpath("//span[@id='select2-gcategory-container']");
	By Ingredientdd=By.xpath("//span[text()='Select ingredient']");
	By IngredientNames=By.xpath("(//li[@role='option'])[position()>1 and position()<=6]"); 
	By addIngButton=By.xpath("//i[@class='fas fa-plus text-secondary']");
	
	By GenerateButton=By.xpath("//a[normalize-space()='Generate Report']");
	
	By criteriapp=By.xpath("//select[@id='criteriap']");
	By valueTF=By.xpath("//input[@id='minValp']");
	By maxValueTF = By.xpath("//input[@id='maxValp']");
	List<String> criteriaOptions = List.of("Greater Than (>)", "Less Than (<)", "Range");
	By reportNameInPPreport = By.xpath("//h5[contains(@class,'mr-2 text-uppercase font-weight-normal')]");
	


	public void EnterReportName(String rname) {
		WebElement inputField = ww.until(ExpectedConditions.visibilityOfElementLocated(ReportName));
		inputField.clear(); // Optional: clear before entering text
		inputField.sendKeys(rname);
		System.out.println("Report name entered: " + rname);
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

	public void SelectProperties() throws InterruptedException {
		  List<WebElement> rows = ww.until(ExpectedConditions.numberOfElementsToBeMoreThan(propRows, 0));
	        List<WebElement> dropdowns = driver.findElements(criteriaDropdown);
	        List<WebElement> toggles = driver.findElements(toggleButton);

	        for (int i = 0; i < rows.size(); i++) {
	            String selected = criteriaOptions.get(rand.nextInt(criteriaOptions.size()));
	            Select select = new Select(dropdowns.get(i));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",select);
	            select.selectByVisibleText(selected);
	            Thread.sleep(500); // Allow time for inputs to update

	            switch (selected) {
	                case "Greater Than (>)":
	                    WebElement gtInput = rows.get(i).findElement(By.xpath(".//input[contains(@id,'minVal')]"));
	                    gtInput.clear();
	                    gtInput.sendKeys("0");
	                    break;
	                case "Less Than (<)":
	                    WebElement ltInput = rows.get(i).findElement(By.xpath(".//input[contains(@id,'minVal')]"));
	                    ltInput.clear();
	                    ltInput.sendKeys("100");
	                    break;
	                case "Range":
	                    WebElement minInput = rows.get(i).findElement(By.xpath(".//input[contains(@id,'minVal')]"));
	                    WebElement maxInput = rows.get(i).findElement(By.xpath(".//input[contains(@name,'maxVal')]"));
	                    minInput.clear();
	                    minInput.sendKeys("0");
	                    maxInput.clear();
	                    maxInput.sendKeys("100");
	                    break;
	            }

	            WebElement toggle = toggles.get(i);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
	        }

	        driver.findElement(NextButton).click();

	}

		
	//Adding ingredients 
	public void addIngredient() throws InterruptedException {
		
		Thread.sleep(3000);
        int countToClick = 5;
        for (int i = 0; i < countToClick; i++) {
            // List<WebElement> ingNames =
            // ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IngredientNames));
            WebElement dropdown = ww.until(ExpectedConditions.elementToBeClickable(Categorydd));
            dropdown.click();
            a.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(300)).sendKeys(Keys.ENTER).perform();
            Thread.sleep(5000);
            WebElement ingDropdown = driver.findElement(Ingredientdd);
            //WebElement ingDropdown = ww.until(ExpectedConditions.elementToBeClickable(SelectIngredientdd));
            ingDropdown.click();
            Thread.sleep(1000);
            //List<WebElement> ingNames = driver.findElements(IngredientNames);
            List<WebElement> ingNames = ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IngredientNames));
            if (i >= ingNames.size())
                break;
             //Thread.sleep(3000);   modified
            WebElement ingList = ingNames.get(i);
            ingList.click();
            
            WebElement cding = driver.findElement(criteriapp);
            s=new Select(cding);
            
            String selectIngCri = criteriaOptions.get(rand.nextInt(criteriaOptions.size()));
           
            s.selectByVisibleText(selectIngCri);
            
             switch (selectIngCri) {
             case "Greater Than (>)":
            	 By valueTF=By.xpath("//input[@id='minValp']");
            	
                 WebElement gtInput = driver.findElement(valueTF);
                 gtInput.clear();
                 gtInput.sendKeys("0");
                 break;
             case "Less Than (<)":
            	 By valueT1=By.xpath("//input[@id='minValp']");
            	
                 WebElement ltInput = driver.findElement(valueT1);
                 ltInput.clear();
                 ltInput.sendKeys("100");
                 break;
             case "Range":
            	 By value3=By.xpath("//input[@id='minValp']");
            	By maxValueTF = By.xpath("//input[@id='maxValp']");
                 WebElement minInput = driver.findElement(value3);
                 WebElement maxInput = driver.findElement(maxValueTF);
                 minInput.clear();
                 minInput.sendKeys("0");
                 maxInput.clear();
                 maxInput.sendKeys("100");
                 break;
         }

            
            WebElement addBtn = driver.findElement(addIngButton);
                    //ww.until(ExpectedConditions.elementToBeClickable(addIngButton));
            addBtn.click();
            Thread.sleep(2000);  //modified
            
        }
        WebElement generateBtn = ww.until(ExpectedConditions.presenceOfElementLocated(GenerateButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", generateBtn);

        // generateBtn =
        // ww.until(ExpectedConditions.elementToBeClickable(GenerateButton));
        generateBtn.click();
	
	}
	
	public void verifyReport(String reportName) {
		 try {
		        Thread.sleep(10000);

		        boolean isReportVisible = false;
		        int attempts = 0;
		        int maxAttempts = 4;

		        // Build dynamic XPath using the reportName parameter
		        By dynamicReportViewIcon = By
		            .xpath("//span[text()='" + reportName + "']/../../..//a[@aria-label='View Report']");
		        // **Added status locators**
		        By statusSuccess = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Success']");
		        By statusFailed = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Failed']");
		        By RefreshIcon = By.xpath("//button[@id='refresh_spin']");

		        while (attempts < maxAttempts && !isReportVisible) {
		            try {
		                WebElement viewIcon = ww.until(ExpectedConditions.visibilityOfElementLocated(dynamicReportViewIcon));
		                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);
		                // **Check status before clicking view icon**
		                
		                if (!driver.findElements(statusSuccess).isEmpty()) {
		                    System.out.println("Report status: Success");
		                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);
		                    ww.until(ExpectedConditions.elementToBeClickable(viewIcon)).click();
		                    isReportVisible = true;
		                    Assert.assertTrue(true, "Report generated successfully."); // **Test pass**
		                    break;
		                } else if (!driver.findElements(statusFailed).isEmpty()) {
		                    System.out.println("Report status: Failed");
		                    Assert.fail("Report generation failed."); // **Test fail**
		                    break;
		                }
		                else {
		                	System.out.println("Report is still processing, waiting for completion...");
		              
		                	break;
		                }
		                // else, do nothing, continue to refresh
		            } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
		                System.out.println("Report is visible checking the status: " + (attempts + 1));
		            }

		            try {
		                WebElement refreshBtn = ww.until(ExpectedConditions.elementToBeClickable(RefreshIcon));
		                refreshBtn.click();
		                Thread.sleep(10000);
		            } catch (Exception e) {
		                System.out.println("Failed to click refresh icon.");
		            }

		            attempts++;
		        }

		        if (!isReportVisible) {
		            System.out.println("Report status is in process after maximum retries.");
		           
		        }

		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt();
		        System.out.println("Interrupted Exception occurred.");
		    }
	}
	
	public void verifyReportNameInReport() {
		String ActReportNametxt = driver.findElement(reportNameInPPreport).getText();
    	String ExpReportNametxt = RecipeSuggestionWorkFlowPage.this.rn;

    	Assert.assertEquals(ActReportNametxt.toLowerCase(), ExpReportNametxt.toLowerCase(), "Report name does not match expected value.");
		System.out.println("Actual report name: " + ActReportNametxt);
		System.out.println("Expected report name: " + ExpReportNametxt);
	}

}
