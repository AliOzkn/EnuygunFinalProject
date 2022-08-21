package testscenarios;

import loglistener.LogListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utilities.DriverSetup;
import utilities.ReadConfig;

import java.time.Duration;
import java.util.Properties;

@Listeners(LogListener.class)
public class BaseTest {
    static WebDriver driver;
    static Properties properties;
    WebDriverWait wait;


    @BeforeSuite
    public void setUp() {
        properties = ReadConfig.initializeProperties();
        driver = DriverSetup.initializeDriver(properties.getProperty("browser"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @AfterSuite
    public void driverClose() {
        driver.close();
    }


}
