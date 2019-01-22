package lib;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BasePage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTestClass {

    public static AndroidDriver driver;
    public static String appiumUrl;
    public static String appPath;
    public static String appPackage;
    public static String appActivity;
    protected static Properties properties;
    protected static Logger logger = Logger.getInstance();

    @BeforeClass
    public static void openUrl() throws MalformedURLException {
        setProperties();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("app", appPath);

        driver = new AndroidDriver(new URL(appiumUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        BasePage page = new BasePage(driver);
        page.setDriver(driver);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public static synchronized void setProperties() {
        properties = new Properties();
        try {
            properties.load(BaseTestClass.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.warn("Cannot read config.properties file");
        }
        appiumUrl = properties.getProperty("appiumUrl");
        appPath = properties.getProperty("appPath");
        appPath = new File("").getAbsolutePath() + appPath;
        appPackage = properties.getProperty("appPackage");
        appActivity = properties.getProperty("appActivity");
    }

    public void assertEquals(String messageOK, String messageWrong, final Object expected, final Object actual) {
        if (!expected.equals(actual)) {
            logger.fatal(messageWrong + ", Expected value: '" + expected + "', but was: '" + actual
                    + "'");
        } else {
            logger.info(messageOK + " ...OK");
        }
    }
/*
    @ClassRule
    public static TestRule classWatcher = new TestRule() {
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    logger.info(description.getDisplayName() + " class started");
                    base.evaluate();
                    logger.info(description.getDisplayName() + " class finished");
                }
            };
        }
    };

    @Rule
    public TestRule watchman = new TestWatcher() {
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        logger.info(description.getDisplayName() + " test started");
                        base.evaluate();
                        logger.info(description.getDisplayName() + ": was succesfull");
                    } catch (Throwable t) {
                        logger.error(description.getDisplayName() + ": failed");
                        throw t;
                    }
                }
            };
        }
    };
*/
}
