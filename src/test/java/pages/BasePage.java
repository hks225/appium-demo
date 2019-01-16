package pages;

import io.appium.java_client.AppiumDriver;
import lib.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected AppiumDriver driver;
    protected WebDriverWait loadingWait;
    protected WebDriverWait shortWait;
    protected WebDriverWait veryShortWait;
    protected WebDriverWait actionWait;
    protected WebDriverWait defaultWait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.veryShortWait = new WebDriverWait(driver, Timeout.veryShortTimeout);
        this.shortWait = new WebDriverWait(driver, Timeout.shortTimeout);
        this.actionWait = new WebDriverWait(driver, Timeout.action);
        this.loadingWait = new WebDriverWait(driver, Timeout.loading);
        this.defaultWait = new WebDriverWait(driver, Timeout.defaultTimeout);
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //
        }
    }

    public List<WebElement> getVisibleElements(By locator) {
        List<WebElement> visibleElements = new ArrayList<>();
        for (WebElement element : getElements(locator)) {
            if (element.isDisplayed()) {
                visibleElements.add(element);
            }
        }
        if (visibleElements.size() > 0) {
            return visibleElements;
        }
        throw new NoSuchElementException("No visible elements present with locator: '" + locator + "'");
    }

    public List<WebElement> getElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (InvalidSelectorException e) {
            return new ArrayList<WebElement>(); // empty list, chrome case
        }
    }

    public WebElement getVisibleElement(By locator) {
        for (WebElement element : getElements(locator)) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        throw new NoSuchElementException("No visible element present with locator: '" + locator + "'");
    }


}
