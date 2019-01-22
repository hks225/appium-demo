import io.qameta.allure.Description;
import lib.BaseTestClass;

import org.testng.annotations.Test;
import pages.FormulaPage;
import pages.LandingPage;

public class SmokeTestClass extends BaseTestClass {

    private LandingPage landingPage;
    private FormulaPage formulaPage;

    @Test
    @Description("Smoke test")
    public void smokeTest() {
        landingPage = new LandingPage(driver);
        formulaPage = landingPage.clickOnFormulaButton();
        formulaPage.fillFormulaForm("10", "10", "10")
        .clickOnCalculateButton();
        String resultText = formulaPage.getCalculationsText();
        assertEquals("Check of result", "Wrong result",
                "79.53981633974483", resultText);
    }

}
