package pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class consoleLandingPage extends BasePage {

	public consoleLandingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	    By workspacedd=By.xpath("//select[@id='login_method' or @name='login_method']");
	    By ProfileIcon=By.id("profileDropdown");
	    By sabicIcon=By.xpath("//div[@class='nav-item']//img");
	    By dashboardButton=By.xpath("//span[normalize-space()='Dashboard']");
	    By performancePredictionBtn=By.xpath("//button[contains(@class,'performance_prediction_btn')]");
	    By RecipeSuggestionBtn=By.xpath("//button[contains(@class,'recipe_suggestion_btn')]");
	    By RecipeSearchBtn=By.xpath("//button[contains(@class,'recipe_search_btn')]");
	    By PerformanceSearchBtn=By.xpath("//button[contains(@class,'performance_search_btn')]");
	    By MixedsearchBtn=By.xpath("//button[contains(@class,'mixed_search_btn')]");
	    By DataExplorationBtn=By.xpath("//button[contains(@class,'data_exploration_btn')]");
	    
	    String parentWindow = driver.getWindowHandle(); 
	    Set<String> allWindows = driver.getWindowHandles();
	    
	    public void ClickonDashBoard() {
	        driver.findElement(dashboardButton).click();
	    }
	    
	    public void clickonPerfPred() {
	        

	        for (String windowHandle : allWindows) {
	            if (!windowHandle.equals(parentWindow)) {
	                driver.switchTo().window(windowHandle); 
	                break; 
	            }
	        }
	        driver.findElement(performancePredictionBtn).click();
	    }
	    
	    public void clickonRecipeSuggestion() {
//	        for (String windowHandle : allWindows) {
//	            if (!windowHandle.equals(parentWindow)) {
//	                driver.switchTo().window(windowHandle); 
//	                break; 
//	            }
//	        }
	        driver.findElement(RecipeSuggestionBtn).click();
	    }
	    
	    public void clickonRecipeSearch() {
//	       for (String windowHandle : allWindows) {
//	           if (!windowHandle.equals(parentWindow)) {
//	                driver.switchTo().window(windowHandle); 
//	                break; 
//	           }
//	        }
	        
	        driver.findElement(RecipeSearchBtn).click();
	    }
	    
	    public void clickonPerformanceSearch() {
//	        for (String windowHandle : allWindows) {
//	            if (!windowHandle.equals(parentWindow)) {
//	                driver.switchTo().window(windowHandle); 
//	                break; 
//	            }
//	        }
//	        
	        driver.findElement(PerformanceSearchBtn).click();
	    }
	    
	    public void clickonMixedSearch() {
//	        for (String windowHandle : allWindows) {
//	            if (!windowHandle.equals(parentWindow)) {
//	                driver.switchTo().window(windowHandle); 
//	                break; 
//	            }
//	        }
	        driver.findElement(MixedsearchBtn).click();
	    }
	    
	    public void clickOnDataExploration() {
//	      for (String windowHandle : allWindows) {
//	            if (!windowHandle.equals(parentWindow)) {
//	                driver.switchTo().window(windowHandle); 
//	               break; 
//	           }
//	        }
	        driver.findElement(DataExplorationBtn).click();
	    }

}
