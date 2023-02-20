package pages.Locators;

import org.openqa.selenium.By;

public interface TicketPlace {

    By originInput = By.xpath("//input[@inputname='origin']");
    By destinationInput = By.xpath("//input[@inputname='destination']");
    By firstResult = By.xpath("//ul[@role='listbox']/li[1]");
}
