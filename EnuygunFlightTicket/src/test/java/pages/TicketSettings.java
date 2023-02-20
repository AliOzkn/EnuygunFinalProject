package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Locators.FlightType;
import pages.Locators.TicketDate;
import pages.Locators.TicketPlace;
import utilities.DriverSetup;

import java.util.Calendar;
import java.util.Date;

public class TicketSettings extends DriverSetup implements TicketPlace, TicketDate, FlightType {

    public String departureDay;
    public String returnDay;
    public String departureDate;
    public String returnDate;

    /* ============================================================================================================================================================================ */
    // PLACE SETTINGS

    // From where
    public void setOrigin() {
        wait.until(ExpectedConditions.elementToBeClickable(originInput));
        driver.findElement(originInput).sendKeys(DriverSetup.properties.getProperty("origin"));
        wait.until(ExpectedConditions.elementToBeClickable(firstResult));
        driver.findElement(firstResult).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originInput));
    }

    // To where
    public void setDestination() {
        wait.until(ExpectedConditions.elementToBeClickable(destinationInput));
        driver.findElement(destinationInput).sendKeys(DriverSetup.properties.getProperty("destination"));
        wait.until(ExpectedConditions.elementToBeClickable(firstResult));
        driver.findElement(firstResult).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(destinationInput));
    }

    // Assertion of origin place
    public boolean getOrigin() {
        return driver.findElement(originInput).getAttribute("value").toLowerCase().contains(DriverSetup.properties.getProperty("origin"));
    }

    // Assertion of destination place
    public boolean getDestination() {
        return driver.findElement(destinationInput).getAttribute("value").toLowerCase().contains(DriverSetup.properties.getProperty("destination"));
    }

    /* ============================================================================================================================================================================ */
    // TIME SETTINGS

    // Select departure day
    public void selectDepartureDay() {
        // Gets today's date.
        c1.setTime(new Date());
        // It calculates the new date by adding the number of days entered the "departureDay" variable to today's date.
        c1.add(Calendar.DATE, Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
        String departureMonthAndYear = monthAndYearFormat.format(c1.getTime());
        this.departureDay = onlyDayFormat.format(c1.getTime());
        this.departureDate = fullFormat.format(c1.getTime());

        wait.until(ExpectedConditions.elementToBeClickable(departureDateInput));
        driver.findElement(departureDateInput).click();

        while (true) {
            wait.until(ExpectedConditions.elementToBeClickable(monthAndYearOnCalendar));
            driver.findElement(monthAndYearOnCalendar).click();
            String departureMonthAndYearText = driver.findElement(monthAndYearOnCalendar).getText();

            if (departureMonthAndYearText.equals(departureMonthAndYear)) {
                driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.departureDay + "']")).click();
                break;
            } else {
                driver.findElement(nextMonthBtn).click();
            }
        }
    }

    // Select retun day
    public void selectReturnDay()  {

        c1.setTime(new Date());
        c1.add(Calendar.DATE, (Integer.parseInt(DriverSetup.properties.getProperty("departureDay"))) + (Integer.parseInt(DriverSetup.properties.getProperty("returnDay"))));
        String returnMonthAndYear = monthAndYearFormat.format(c1.getTime());
        this.returnDay = onlyDayFormat.format(c1.getTime());
        this.returnDate = fullFormat.format(c1.getTime());

        wait.until(ExpectedConditions.elementToBeClickable(oneWayBtn));
        driver.findElement(oneWayBtn).click();

        while (true) {
            wait.until(ExpectedConditions.elementToBeClickable(monthAndYearOnCalendar));
            driver.findElement(monthAndYearOnCalendar).click();
            String returnMonthAndYearText = driver.findElement(monthAndYearOnCalendar).getText();

            if (returnMonthAndYearText.equals(returnMonthAndYear)) {
                driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.returnDay + "']")).click();
                break;
            } else {
                driver.findElement(nextMonthBtn).click();
            }
        }
    }

    // Assertion of departure day
    public boolean getDepartureDay() {
        return driver.findElement(departureDateInput).getAttribute("value").contains(this.departureDate);
    }

    // Assertion of return day
    public boolean getReturnDay() {
        return driver.findElement(returnDateInput).getAttribute("value").contains(this.returnDate);
    }

    /* ============================================================================================================================================================================ */
    // FLIGHT TYPE SETTINGS

    // For using direct flight setting
    public void clickDirectFlightsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(directFlights));
        driver.findElement(directFlights).click();
    }

    // Search for ticket
    public void clickFindTicketBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(findTicketBtn));
        driver.findElement(findTicketBtn).click();
    }

    // Assertion of direct flights
    public boolean isDirectFlightsSelected() {
        return driver.findElement(directFlights).isSelected();
    }


}
