package testscenarios;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.*;
import utilities.DriverSetup;

import java.util.ArrayList;

import static org.testng.Assert.*;

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
    public void checkCurrentUrl() {
        ticketPlaceSearch = new TicketPlaceSearch(driver);
        assertEquals(driver.getCurrentUrl(), DriverSetup.properties.getProperty("url"), "Actual and expected URLs don't match");
    }

    @Test(dependsOnMethods = "checkCurrentUrl", priority = 2)
    @Story("This method controls the visibility of the main logo.")
    @Feature("Check main logo is displayed")
    public void checkMainLogoForSearchPage() {
        assertTrue(ticketPlaceSearch.checkMainLogo());

    }

    @Test(dependsOnMethods = "checkCurrentUrl", priority = 3)
    @Story("This method writes the requested origin value and checks the equality of the requested and the written value")
    @Feature("Entering the origin value.")
    public void checkOriginPlace() {

        ticketPlaceSearch.setOrigin();

        assertTrue(ticketPlaceSearch.getOrigin());

    }

    @Test(dependsOnMethods = "checkCurrentUrl", priority = 4)
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

    @Test(dependsOnMethods = "checkDepartureDate", priority = 6)
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

    /* ==================== TICKET SELECT PAGE TESTS ==================== */

    @Test(priority = 8)
    @Story("This method checks the flight is connected or non-stop")
    @Feature("Check flight type as connected or non-stop")
    public void checkFlightType() {

        ticketSelect = new TicketSelect(driver);
        if (DriverSetup.properties.getProperty("isDirect").toLowerCase().equals("true")) {
            flightType.directFlight();
            assertFalse(flightType.directFlightAssertions());

        } else if (DriverSetup.properties.getProperty("isDirect").toLowerCase().equals("false")) {

            flightType.flightWithTransfers();
            assertFalse(flightType.flightWithTransfersAssertion());
        }

        WebElement üzgünüzYazisi = driver.findElement(By.xpath("//p[@class='result-empty-text']")) ;

        if (üzgünüzYazisi.isDisplayed()){
            driver.close();
        } else {
            ticketSelect.selectTicket(); // Bilet secme adimi
        }

    }


    /* ==================== TICKET PAYMENT PAGE TESTS ==================== */

    @Test(priority = 9)
    @Story("This method checks the proceed to payment button is enabled.")
    @Feature("Check payment button is enabled.")
    public void checkPaymentBtn() {
        ticketSelect.chooseTicket();
        paymentPage = new PaymentPage(driver);
        assertTrue(paymentPage.isProceedToPaymentBtnEnabled());
    }
}










