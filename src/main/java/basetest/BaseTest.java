package basetest;

import browsertype.BrowserType;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    private static WebDriver driver;

    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(String browserName) {
     setupDriver(BrowserType.valueOf(browserName));
    }

    @AfterMethod
    public void afterMethod() {
        if(driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void getBrowser(BrowserType browserType) {

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                driver = null;
        }
    }

    private void setupDriver(BrowserType browserType){
        getBrowser(browserType);
        driver.manage().window().maximize();
    }


}
