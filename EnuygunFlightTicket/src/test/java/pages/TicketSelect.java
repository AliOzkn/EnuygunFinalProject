package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Locators.TicketSelectPage;
import utilities.DriverSetup;

public class TicketSelect extends DriverSetup implements TicketSelectPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Assertion of ticket select page
    public boolean isTicketSelectPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ticketSelectPage));
        return driver.findElement(ticketSelectPage).isDisplayed();
    }


    public void chooseTicket() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(departureProvider));
        driver.findElement(departureProvider).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(returnProvider));
        driver.findElement(returnProvider).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(chooseBtn));
        driver.findElement(chooseBtn).click();
    }

    // Assertion of payment page
    public boolean isProceedToPaymentBtnDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(proceedToPaymentBtn));
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(proceedToPaymentBtn));
        driver.findElement(proceedToPaymentBtn).isDisplayed();
        return true;
    }


}












