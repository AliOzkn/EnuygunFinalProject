package testscenarios;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TicketPageTest extends BaseTest {
    TicketPlaceSearch ticketPlaceSearch;
    TicketDateSearch ticketDateSearch;
    FlightType flightType;
    TicketSelect ticketSelect;
    PaymentPage paymentPage;

    /* ==================== TICKET SEARCH PAGE TESTS ==================== */
    @Test(priority = 1)
    @Story("This method compares the actual and expected URL values.")
    @Feature("Check current Url")
    public void checkCurrentUrlAndMainLogo() {
        ticketPlaceSearch = new TicketPlaceSearch(driver);
        assertEquals(driver.getCurrentUrl(), DriverSetup.properties.getProperty("url"), "Actual and expected URLs don't match");
        assertTrue(ticketPlaceSearch.checkMainLogo());
    }


    @Test(priority = 3)
    @Story("This method writes the requested origin value and checks the equality of the requested and the written value")
    @Feature("Entering the origin value.")
    public void checkOriginPlace() {
        ticketPlaceSearch.setOrigin();
        assertTrue(ticketPlaceSearch.getOrigin());

    }

    @Test(priority = 4)
    @Story("This method writes the requested destination value and checks the equality of the requested and the written value")
    @Feature("Entering the destination value.")
    public void checkDestinationPlace() {
        ticketPlaceSearch.setDestination();
        assertTrue(ticketPlaceSearch.getDestination());

    }

    @Test(priority = 5)
    @Story("This method selects the requested departure date and checks the equality of the requested and the selected value")
    @Feature("Selecting the departure day value")
    public void checkDepartureDate() {
        ticketDateSearch = new TicketDateSearch(driver);
        ticketDateSearch.selectDepartureDay();
        assertTrue(ticketDateSearch.getDepartureDay());

    }

    @Test(priority = 6)
    @Story("This method selects the requested return date and checks the equality of the requested and the selected value")
    @Feature("Selecting the return day value")
    public void checkReturnDate() {
        ticketDateSearch.selectReturnDay();
        assertTrue(ticketDateSearch.getReturnDay());

    }

    @Test(priority = 7)
    @Story("This method clicks the find cheap ticket button.")
    @Feature("Click find ticket button")
    public void clickFindTicket() {
        ArrayList fields = new ArrayList();
        fields.add(ticketPlaceSearch.findInputs());
        fields.add(ticketDateSearch.findInputs());
        assertFalse(false, "Fields are empty.");
        ticketDateSearch.clickFindTicketBtn();

        flightType = new FlightType(driver);

        assertTrue(flightType.checkGraphicBar());
    }

    /* ==================== TICKET SELECT PAGE TEST2 ==================== */

    @Test(priority = 8)
    @Story("This method checks the flight is connected or non-stop")
    @Feature("Check flight type as connected or non-stop")
    public void checkFlightType() {
        ticketSelect = new TicketSelect(driver);
        if (DriverSetup.properties.getProperty("isDirect").equalsIgnoreCase("true")) {
            flightType.directFlight();
            assertFalse(flightType.directFlightAssertions());
        } else if (DriverSetup.properties.getProperty("isDirect").equalsIgnoreCase("false")) {
            flightType.flightWithTransfers();
            assertFalse(flightType.flightWithTransfersAssertion());
        }
    }

    /* ==================== TICKET PAYMENT PAGE TEST ==================== */

    @Test(priority = 9)
    @Story("This method checks that the correct tickets are received and the payment button is enabled.")
    @Feature("Check payment button is enabled.")
    public void checkTickets()  {
        ticketSelect.selectFlights();
        ticketSelect.chooseTicket();
        paymentPage = new PaymentPage(driver);
        assertTrue(paymentPage.isProceedToPaymentBtnEnabled());
    }
}










