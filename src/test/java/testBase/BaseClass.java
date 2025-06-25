package testBase;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
    
    @BeforeTest(alwaysRun = true) 
    @Parameters({"os","browser"})
    public void launch(String os, String br) {
        lg=LogManager.getLogger(this.getClass());

        switch(br.toLowerCase()) {
        case "chrome" : driver=new ChromeDriver(); break;
        case "edge": driver=new EdgeDriver(); break;
        case "firefox": driver = new FirefoxDriver(); break;
        default : System.out.println("select valid browser");
        }
        
        config = new ConfigReader();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        
        String baseUrl = config.getBaseURL();

        driver.get(baseUrl);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
            driver.quit();   
    }

	


   
}
