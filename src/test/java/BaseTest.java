import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    private static WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public static WebDriver getDriver(){
        return driver;
    }


}
