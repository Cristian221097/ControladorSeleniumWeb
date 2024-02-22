package pages;

import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage{

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void goGoogle(){
        go("https://www.google.com/");
    }
}
