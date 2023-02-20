package testscenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.DriverSetup;

import static org.testng.Assert.*;

public class FlightTicketTest extends DriverSetup {
    @BeforeClass
    public void setup() {
        properties = ConfigReader.initializeProperties();
        initializeDriver(DriverSetup.properties.getProperty("browser"));
        ticketSettings = new TicketSettings();
        ticketSelect = new TicketSelect();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }


    /* ==================== TICKET SEARCH PAGE TESTS ==================== */
    @Test(priority = 1,description ="Verifies current url")
    public void verifyCurrentURL() {

        assertEquals(driver.getCurrentUrl(), DriverSetup.properties.getProperty("url"), "Actual and expected URLs don't match");
    }


    @Test(priority = 2,description ="Verifies that have selected the desired origin place.")
    public void verifyOriginPlace() {
        ticketSettings.setOrigin();
        assertTrue(ticketSettings.getOrigin());

    }

    @Test(priority = 3,description ="Verifies that have selected the desired destination place.")
    public void verifyDestinationPlace() {
        ticketSettings.setDestination();
        assertTrue(ticketSettings.getDestination());
    }

    @Test(priority = 4,description ="Verifies that have selected the desired departure date.")
    public void verifyDepartureDate() {
        ticketSettings.selectDepartureDay();
        assertTrue(ticketSettings.getDepartureDay());

    }

    @Test(priority = 5,description ="Verifies that have selected the desired return date.")
    public void verifyReturnDate()  {
        ticketSettings.selectReturnDay();
        assertTrue(ticketSettings.getReturnDay());
    }


    /* ==================== TICKET SELECT PAGE TESTS ==================== */

    @Test(priority = 6,description ="Verifies that have selected the desired flight type (With transfer on non-transfer).")
    public void verifyFlightType() {

        if (DriverSetup.properties.getProperty("isDirect").equalsIgnoreCase("true")) {
            ticketSettings.clickDirectFlightsBtn();
            assertTrue(ticketSettings.isDirectFlightsSelected());
        } else if (DriverSetup.properties.getProperty("isDirect").equalsIgnoreCase("false")) {
            assertTrue(ticketSettings.isDirectFlightsSelected());
        }
    }

    @Test(priority = 7,description = "Verifies that the ticket select page is opened.")
    public void verifyTicketSelectPage() {
        ticketSettings.clickFindTicketBtn();
        assertTrue(ticketSelect.isTicketSelectPageDisplayed());
    }

    /* ==================== TICKET PAYMENT PAGE TEST ==================== */

    @Test(priority = 8,description = "Verifies that the 'proceed to payment' button appears after ticket selection.")
    public void verifySelectedTicket() {
        ticketSelect.chooseTicket();
        assertTrue(ticketSelect.isProceedToPaymentBtnDisplayed());
    }



}










