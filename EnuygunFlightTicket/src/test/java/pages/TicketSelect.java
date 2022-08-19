package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DriverSetup;

import java.util.List;

public class TicketSelect extends BasePage {
    By firstResult = By.xpath("(//div[@data-booking-provider])[1]");
    By firstResultOfDepartureFlight = By.xpath("(//div[@class='roundTrip departure'])[1]");
    By firstResultOfReturnFlight = By.xpath("//div[@class='roundTrip return active']//label[contains(@class,'matrixLabel')]");
    By chooseBtn = By.id("tooltipTarget_0");


    public TicketSelect(WebDriver driver) {
        super(driver);
    }

    public void selectFlights() throws InterruptedException {
        List<WebElement> noFoundFlights = driver.findElements(By.xpath("//p[@class='result-empty-text']"));
        if (noFoundFlights.isEmpty()) {
            if (getAttribute(firstResult, "data-booking-provider").contains(DriverSetup.properties.getProperty("provider").toLowerCase())
                    || getAttribute(firstResult, "data-booking-provider").contains("sabre")
                    || getAttribute(firstResult, "data-booking-provider").contains("galileo")
                    || getAttribute(firstResult, "data-booking-provider").contains("amadeus")) {
                click(firstResultOfDepartureFlight);
                Thread.sleep(2000);
                click(firstResultOfReturnFlight);
                Thread.sleep(2000);


            } else {
                System.out.println("uygun ucus bulunamadi");
                driver.close();
            }

        } else {
            driver.close();
        }
    }

    public void chooseTicket() {

        click(chooseBtn);
    }


}












