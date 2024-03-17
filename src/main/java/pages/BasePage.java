package pages;

import helpers.HelperFile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;


public class BasePage {
    protected WebDriver driver;
    private Logger logger;
    private WebDriverWait wait;

    private static final int TIMEWAIT = 90;

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
            confirmPresentElement(locator, nameElement);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (Exception err) {
            logger.info(nameElement + " " + err.getMessage());
            Assert.fail();
        }
    }

    public void writeField(By locator, String value, String nameElement) {
        try {
            confirmPresentElement(locator, nameElement);
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

    private void jsExecuter(String script) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
        } catch (Exception err) {
            logger.info("Error al ejecutar el script " + script + " " + err.getMessage());
            Assert.fail();
        }
    }

    private void jsExecuter(String script, By locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script, driver.findElement(locator));
        } catch (Exception err) {
            logger.info("Error al ejecutar el script " + script + " " + err.getMessage());
            Assert.fail();
        }
    }

    public void clickExecuterJS(By locator, String nameElement) {
        try {
            confirmPresentElement(locator, nameElement);
            jsExecuter("arguments[0].click();", locator);
        } catch (Exception err) {
            logger.info("Error al hacer click en el elemento: " + nameElement);
            Assert.fail();
        }
    }

    public void confirmIsValueAEqualsValueB(String valueA, String valueB) {
        if (!valueA.equals(valueB)) {
            logger.info("Los valores ingresados no son iguales " + valueA);
            Assert.fail();
        }
    }

    public void confirmIsValueAEqualsValueB(int valueA, int valueB) {
        if (valueA != valueB) {
            logger.info("Los valores ingresados no son iguales " + valueA);
            Assert.fail();
        }
    }

    public void waitDownloadFile(String path, String nameFile) {
        try {
            wait.until((ExpectedCondition<Boolean>) isDownload -> HelperFile.getInstance().isExitFile(path, nameFile));

        } catch (Exception err) {
            logger.info("El archivo: " + nameFile + " no se encuentra en el path: " + path);
            Assert.fail();
        }
    }


}
