package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;


public class BasePage {
    private WebDriver driver;
    private Logger logger;
    private WebDriverWait wait;

    private static final int TIMEWAIT = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEWAIT));
    }

    public void confirmPresentElement(By locator, String nameElement) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception err) {
            logger.info("El sistema no muestra en pantalla el elemento: " + nameElement);
            Assert.fail();
        }
    }

    public void clickElement(By locator, String nameElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (Exception err) {
            logger.info(nameElement + " " + err.getMessage());
            Assert.fail();
        }
    }

    public void writeField(By locator, String value, String nameElement) {
        try {
            driver.findElement(locator).sendKeys(value);
        } catch (Exception err) {
            logger.info("Error al escribir el valor: " + value + " en el elemento: " + nameElement);
            Assert.fail();
        }
    }

    public void confirmIsVisibleElement(By locator, String nameElement) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception err) {
            logger.info("El sistema no muestra en pantalla el elemento: " + nameElement);
            Assert.fail();
        }
    }

    public void confirmNotVisibleElement(By locator, String nameElement) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception err) {
            logger.info("El sistema muestra en pantalla el elemento: " + nameElement);
            Assert.fail();
        }
    }

    public void go(String url) {
        driver.get(url);
    }


}
