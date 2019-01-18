package pages;

import io.appium.java_client.AppiumDriver;
import lib.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

    private final Button formulaButton = new Button(By.xpath("//*[contains(@resource-id,'buttonActivTwo')]"), "By Formula");

    public LandingPage(AppiumDriver driver) {
        super(driver);
        actionWait.until(ExpectedConditions.presenceOfElementLocated(formulaButton.getLocator()));
    }

    public FormulaPage clickOnFormulaButton() {
        formulaButton.click();
        return new FormulaPage(driver);
    }

}
