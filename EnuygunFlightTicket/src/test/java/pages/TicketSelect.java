package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DriverSetup;

import java.util.List;

public class TicketSelect extends BasePage {

    By chooseBtn = By.id("tooltipTarget_0");


    public TicketSelect(WebDriver driver) {
        super(driver);
    }

    public void selectFlights() {
        List<WebElement> flightNotFound = driver.findElements(By.xpath("//p[@class='result-empty-text']"));
        List<WebElement> departureFlights = driver.findElements(By.xpath("//div[@class='roundTrip departure']//div[@data-booking-provider]"));
        List<WebElement> returnFlights = driver.findElements(By.xpath("//div[@class='roundTrip return']//div[@data-booking-provider]"));

        if (flightNotFound.isEmpty()) {

            for (int i = 0; i < 10; i++) {

                if (departureFlights.get(i).getAttribute("data-booking-provider").contains(DriverSetup.properties.getProperty("provider").toLowerCase())
                        || departureFlights.get(i).getAttribute("data-booking-provider").contains("sabre")
                        || departureFlights.get(i).getAttribute("data-booking-provider").contains("amadeus")
                        || departureFlights.get(i).getAttribute("data-booking-provider").contains("galileo")) {
                    try {
                        departureFlights.get(i).click();
                        Thread.sleep(1000);
                        returnFlights.get(i).click();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                } else {
                    System.out.println("Provider ile uyumlu ucus bulunamadi.");
                    driver.close();
                }
            }

        } else {
            System.out.println("Belirtilen aktarma ve tarih kriterlerine uygun ucus bulunamadi.");
            driver.close();
        }
    }

    public void chooseTicket() {
        click(chooseBtn);
    }
}












