package lib;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Logger {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
    private static Logger instance = null;

    private Logger() {
    }

    /**
     * Implementation of the Singleton pattern
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    @Step("{message}")
    public void info(String message) {
        logger.info(message);
    }

    public void infoWithoutAllureStep(String message) {
        logger.info(message);
    }

    @Step("WARNING - {message}")
    public void warn(String message) {
        logger.warn(message);
    }

    @Step("ERROR - {message}")
    public void error(String message) {
        logger.error(message);
    }

    @Step("FATAL - {message}")
    public void fatal(String message) {
        logger.fatal(message);
        Assert.assertTrue(false, message);
    }

}
