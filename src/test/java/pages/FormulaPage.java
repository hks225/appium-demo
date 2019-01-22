package pages;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.elements.Button;
import lib.elements.Label;
import lib.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormulaPage extends BasePage {

    private final Button clearButton = new Button(By.xpath("//*[contains(@resource-id,'buttonClear;)]"), "Clear");
    private final TextBox cylinderDiameterInput = new TextBox(By.xpath("//*[contains(@resource-id,'cilindrDiametr')]"), "Cylinder Diameter");
    private final TextBox pistonStrokeInput = new TextBox(By.xpath("//*[contains(@resource-id,'hodPorshni')]"), "Piston Stroke");
    private final TextBox combustionChamberVolumeInput = new TextBox(By.xpath("//*[contains(@resource-id,'obemKameriSgorania')]"), "Combustion Chamber Volume");
    private final Button calculateButton = new Button(By.xpath("//*[contains(@resource-id,'calculate')]"), "Calculate");
    private final Label resultLabel = new Label(By.xpath("//*[contains(@resource-id,'result')]"), "Result");

    public FormulaPage(AppiumDriver driver) {
        super(driver);
        actionWait.until(ExpectedConditions.presenceOfElementLocated(calculateButton.getLocator()));
    }

    public FormulaPage setCylinderDiameter(String diameter) {
        cylinderDiameterInput.setText(diameter);
        return this;
    }

    public FormulaPage setPistonStroke(String pistonStroke) {
        pistonStrokeInput.setText(pistonStroke);
        return this;
    }

    public FormulaPage setCombustionChamberVolume(String volume) {
        combustionChamberVolumeInput.setText(volume);
        return this;
    }

    @Step("fillFormulaForm {0} {1} {2}")
    public FormulaPage fillFormulaForm(String diameter, String pistonStroke, String volume) {
        setCylinderDiameter(diameter);
        setPistonStroke(pistonStroke);
        setCombustionChamberVolume(volume);
        return this;
    }

    public FormulaPage clickOnCalculateButton() {
        calculateButton.click();
        return this;
    }

    public FormulaPage clickOnClearButton() {
        clearButton.click();
        return this;
    }

    public String getCalculationsText() {
        return resultLabel.getText();
    }

}
