package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

    private static final By byFormulaButton = By.xpath("//*[contains(@resource-id,'buttonActivTwo')]");

    public LandingPage(AppiumDriver driver) {
        super(driver);
        actionWait.until(ExpectedConditions.presenceOfElementLocated(byFormulaButton));
    }

    public FormulaPage clickOnFormulaButton() {
        getElement(byFormulaButton).click();
        return new FormulaPage(driver);
    }

}
