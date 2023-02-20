package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverSetup {

    public static Properties properties;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public TicketSelect ticketSelect;
    public TicketSettings ticketSettings;

    public DriverSetup() {

    }

    public static WebDriver initializeDriver(String browser) {

        properties = ConfigReader.initializeProperties();
        try {
            switch (browser) {
                case "Chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-notifications");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "Firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("dom. webnotifications.enabled", false);
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "Edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
        } catch (SessionNotCreatedException e) {
            System.out.println("Version Error:" + e.getLocalizedMessage());
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        return driver;
    }
    public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}
