package citrix.gotowebinar.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.logging.Logger;

public class WebDriverTestCase {
    @Rule
    public TestName testName = new TestName();

    protected static Logger log = Logger.getLogger(getTestClassName());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static Properties properties;
    protected static String propertiesFile = "/test.properties";

    @BeforeClass
    public static void initialize() {
        log = Logger.getLogger(getTestClassName());
        log.info("initializing tests...");
        properties = PropertyLoader.LoadProperties(propertiesFile);
    }

    @Before
    public void setup() throws AutomationException {
        log = Logger.getLogger(this.getClass().getSimpleName());
        log.info("setting up test case...");
        driver = new WebDriverFactory(properties).local().browser(Browser.CHROME).getInstance();
        wait = new WebDriverWait(driver, WebDriverFactory.TIMEOUT);
    }

    @After
    public void cleanup() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    protected static String getTestClassName() {
        StackTraceElement[] trace = new Throwable().getStackTrace();
        String testClassName = trace[1].getClassName();
        return testClassName;
    }

}


