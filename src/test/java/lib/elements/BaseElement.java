package lib.elements;

import lib.BaseTestClass;
import lib.Logger;
import lib.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseElement extends BaseTestClass {

    protected String name;
    protected By locator;

    protected static Logger logger = Logger.getInstance();

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public RemoteWebElement getElement() {
        return getElement(true);
    }

    public By getLocator() {
        return locator;
    }

    public String getName() {
        if (name == null) {
            return "Without Name";
        }
        return name;
    }

    public RemoteWebElement getElement(boolean displayed) {
        if (displayed) {
            waitAndAssertIsPresent();
            return (RemoteWebElement) driver.findElement(this.getLocator());
        }
        waitForIsElementPresent();
        return (RemoteWebElement) driver.findElement(this.getLocator());
    }

    public void click() {
        logger.info("Clicking on '" + this.getName() +
                "' with Locator:'" + this.getLocator() + "'");
        waitAndAssertIsPresent();
        getElement().click();
    }

    public String getText() {
        logger.info("Getting text from '" + this.getName() +
                "' with Locator:'" + this.getLocator() + "'");
        waitAndAssertIsPresent();
        return getElement().getText();
    }

    public void waitAndAssertIsPresent() {
        if (!waitForIsElementPresent()) {
            logger.fatal("No element with Name: '" + this.getName()
                    + "' and Locator: '" + this.getLocator() + "'");
        }
    }

    /**
     * Wait for element is present .
     */
    public boolean waitForIsElementPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Timeout.defaultTimeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(this.getLocator()));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }


}
