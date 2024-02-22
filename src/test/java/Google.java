import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GooglePage;

public class Google extends BaseTest{

    private GooglePage googlePage;

    @BeforeMethod
    public void setup(){
        googlePage = new GooglePage(getDriver());
    }

    @Test(description = "Ir a google")
    public void goToGoogle(){
        googlePage.goGoogle();
    }

}
