package lib;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.BasePage;

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
    public static final Logger logger = Logger.getLogger(BaseTestClass.class);

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
        appPackage = properties.getProperty("appPackage");
        appActivity = properties.getProperty("appActivity");
    }

}
