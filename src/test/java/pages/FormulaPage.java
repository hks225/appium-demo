package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormulaPage extends BasePage{

    private static final By clearButton = By.xpath("//*[contains(@resource-id,'buttonClear;)]");
    private static final By cylinderDiameterInput = By.xpath("//*[contains(@resource-id,'cilindrDiametr')]");
    private static final By pistonStrokeInput = By.xpath("//*[contains(@resource-id,'hodPorshnia')]");
    private static final By combustionChamberVolumeInput = By.xpath("//*[contains(@resource-id,'obemKameriSgorania')]");
    private static final By calculateButton = By.xpath("//*[contains(@resource-id,'calculate')]");
    private static final By resultLabel = By.xpath("//*[contains(@resource-id,'result')]");

    public FormulaPage(AppiumDriver driver) {
        super(driver);
        actionWait.until(ExpectedConditions.presenceOfElementLocated(calculateButton));
    }

    public FormulaPage setCylinderDiameter(String diameter) {
        getElement(cylinderDiameterInput).clear();
        getElement(cylinderDiameterInput).sendKeys(diameter);
        return this;
    }

    public FormulaPage setPistonStroke(String pistonStroke) {
        getElement(pistonStrokeInput).clear();
        getElement(pistonStrokeInput).sendKeys(pistonStroke);
        return this;
    }

    public FormulaPage setCombustionChamberVolume(String volume) {
        getElement(combustionChamberVolumeInput).clear();
        getElement(combustionChamberVolumeInput).sendKeys(volume);
        return this;
    }

    public FormulaPage clickOnCalculateButton() {
        getElement(calculateButton).click();
        return this;
    }

    public String getCalculationsText() {
        return getElement(resultLabel).getText();
    }

}
