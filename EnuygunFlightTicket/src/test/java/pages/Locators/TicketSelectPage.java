package pages.Locators;

import org.openqa.selenium.By;

public interface TicketSelectPage {
    By chooseBtn = By.cssSelector("#tooltipTarget_0");
    By ticketSelectPage = By.xpath("//span[contains(text(),'Gidiş Uçuşları')]");
    By proceedToPaymentBtn = By.xpath("//div[@data-class='proceedCheckout']");
    By departureProvider = By.cssSelector("[class='roundTrip departure'] [class='matrixLabel tr']");
    By returnProvider = By.cssSelector("[class='roundTrip return active']");
}
