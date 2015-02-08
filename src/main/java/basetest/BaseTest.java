package basetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static webdriversingleton.WebDriverSingleton.initWebDriver;
import static webdriversingleton.WebDriverSingleton.quit;

public class BaseTest {
    protected final static String BASE_URL = System.getProperty(
            "url", "http://the-internet.herokuapp.com/status_codes");

    @BeforeMethod
    public void setup() {
        initWebDriver(System.getProperty("browser", "firefox")).get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        quit();
    }
}
