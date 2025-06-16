package testBase;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import io.qameta.allure.testng.AllureTestNg;
import utilities.ConfigReader;
@Listeners({AllureTestNg.class})
public class BaseClass {
    
    protected static WebDriver driver;
    protected ConfigReader config;
    public Logger lg;
    JavascriptExecutor js;
    
    public static WebDriver getDriver() {
		return driver;
	}
    
    @BeforeSuite(alwaysRun = true) 
    @Parameters({"os","browser"})
    public void launch(String os, String br) {
        lg=LogManager.getLogger(this.getClass());
//        if (os.equalsIgnoreCase("mac")) {
//			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
//			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
//			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver");
//		} else if (os.equalsIgnoreCase("windows")) {
//			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
//			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
//		} else {
//			System.out.println("Please provide valid OS name");
//		}
        switch(br.toLowerCase()) {
        case "chrome" : driver=new ChromeDriver(); break;
        case "edge": driver=new EdgeDriver(); break;
        case "firefox": driver = new FirefoxDriver(); break;
        default : System.out.println("select valid browser");
        }
        
        config = new ConfigReader();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '80%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        
        String baseUrl = config.getBaseURL();

        driver.get(baseUrl);
//        js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom='80%'");
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

	


   
}
