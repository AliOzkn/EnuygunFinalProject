package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DriverSetup;

public class TicketPlaceSearch extends BasePage {
    By mainLogo = By.xpath("//a[@aria-label='Homepage']");
    By originInput = By.xpath("//input[@inputname='origin']");
    By destinationInput = By.xpath("//input[@inputname='destination']");
    By firstResult = By.xpath("//ul[@role='listbox']/li[1]");


    public TicketPlaceSearch(WebDriver driver) {

        super(driver);
    }

    public boolean checkMainLogo() {

        return isDisplayed(mainLogo);
    }

    public void setOrigin() {
        sendKeys(originInput, DriverSetup.properties.getProperty("origin"));
        click(firstResult);
    }

    public boolean getOrigin() {
        return getAttribute(originInput, "value").contains((DriverSetup.properties.getProperty("origin").toLowerCase()));
    }

    public void setDestination() {
        sendKeys(destinationInput, DriverSetup.properties.getProperty("destination"));
        click(firstResult);
    }

    public boolean getDestination() {
        return getAttribute(destinationInput, "value").contains((DriverSetup.properties.getProperty("destination").toLowerCase()));
    }

    public Object findInputs() {
        find(originInput).getText();
        find(destinationInput).getText();
        return null;
    }
}
