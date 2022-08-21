package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightType extends BasePage {
    By graphicBar = By.xpath("//div[@class='tab-content']");
    By directFlights = By.xpath("//span[text()='Direkt uçuşlar']");
    By oneTransfer = By.xpath("//span[text()='1 aktarma ']");
    By moreThanOneTransfer = By.xpath("//span[text()='2+ aktarma ']");
    By smartTransfer = By.xpath("(//label[@class='custom-control custom-checkbox'])[4]");

    public FlightType(WebDriver driver) {
        super(driver);
    }

    public boolean checkGraphicBar() {
        return isDisplayed(graphicBar);
    }

    public void directFlight() {
        click(oneTransfer);
        click(moreThanOneTransfer);
        click(smartTransfer);

    }

    public boolean directFlightAssertions() {
        isSelected(oneTransfer);
        isSelected(moreThanOneTransfer);
        isSelected(smartTransfer);
        return false;
    }

    public void flightWithTransfers() {
        click(directFlights);

    }

    public boolean flightWithTransfersAssertion() {
        return isSelected(directFlights);
    }





}
