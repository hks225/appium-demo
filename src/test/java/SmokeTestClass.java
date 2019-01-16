import lib.BaseTestClass;
import org.junit.Assert;
import org.junit.Test;
import pages.FormulaPage;
import pages.LandingPage;

public class SmokeTestClass extends BaseTestClass {

    private LandingPage landingPage;
    private FormulaPage formulaPage;

    @Test
    public void smokeTest() {
        landingPage = new LandingPage(driver);
        formulaPage = landingPage.clickOnFormulaButton();
        formulaPage.setCylinderDiameter("10")
        .setCombustionChamberVolume("10")
        .setPistonStroke("10")
        .clickOnCalculateButton();
        String resultText = formulaPage.getCalculationsText();
        Assert.assertEquals("Wrong result",
                "79.53981633974483", resultText);
    }

}
