package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);

    }

    public WebElement find(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        find(locator).click();
    }

    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        find(locator).sendKeys(text);
    }


    public String getText(By locator) {
        return find(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return find(locator).isDisplayed();
    }

    public boolean isEnabled(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return find(locator).isEnabled();
    }

    public boolean isSelected(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return find(locator).isSelected();
    }

}
