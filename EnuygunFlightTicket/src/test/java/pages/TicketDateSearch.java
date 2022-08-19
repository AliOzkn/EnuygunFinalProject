package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.DriverSetup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketDateSearch extends BasePage {
    By departureDateInput = By.xpath("//input[@name='DepartureDate']");
    By returnDateInput = By.xpath("//input[@name='ReturnDate']");
    By oneWayBtn = By.xpath("(//input[@type='checkbox'])[1]");
    By goToNextMonthBtn = By.xpath("(//div[@role='button'])[2]");
    By monthAndYearValue = By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
    By findCheapTicketBtn = By.xpath("//*[text()='Ucuz bilet bul']");
    SimpleDateFormat format1 = new SimpleDateFormat("MMMM yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("d MMM yyyy");
    SimpleDateFormat day = new SimpleDateFormat("d");
    Calendar c1 = Calendar.getInstance();
    public String departureDay;
    public String returnDay;
    public String departureDate;
    public String returnDate;


    public TicketDateSearch(WebDriver driver) {

        super(driver);
    }


    public void selectDepartureDay() {
        // Bugünün tarihini alıyor.
        c1.setTime(new Date());
        // Bugünün tarihine, "departureDay" degiskenine girilen sayi kadar gün ekleyerek, yeni tarihi hesaplıyor.
        c1.add(Calendar.DATE, Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
        String departureMonthAndYearValue = format1.format(c1.getTime());
        this.departureDay = day.format(c1.getTime());
        this.departureDate = format2.format(c1.getTime());

        click(departureDateInput);

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthAndYearValue));
            String departureMonthAndYearText = find(monthAndYearValue).getText();

            if (departureMonthAndYearText.equals(departureMonthAndYearValue)) {
                WebElement departureDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.departureDay + "']"));
                departureDay.click();
                break;
            } else {
                click(goToNextMonthBtn);
            }
        }
    }

    public boolean getDepartureDay() {
        return find(departureDateInput).getAttribute("value").contains(this.departureDate);

    }

    public void selectReturnDay() {

        c1.setTime(new Date());
        c1.add(Calendar.DATE, (Integer.parseInt(DriverSetup.properties.getProperty("departureDay"))) + (Integer.parseInt(DriverSetup.properties.getProperty("returnDay"))));
        String returnMonthAndYearValue = format1.format(c1.getTime());
        this.returnDay = day.format(c1.getTime());
        this.returnDate = format2.format(c1.getTime());

        click(oneWayBtn);

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthAndYearValue));
            String returnMonthAndYearText = find(monthAndYearValue).getText();

            if (returnMonthAndYearText.equals(returnMonthAndYearValue)) {
                WebElement returnDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.returnDay + "']"));
                returnDay.click();
                break;
            } else {
                click(goToNextMonthBtn);
            }
        }
    }

    public boolean getReturnDay() {

        return find(returnDateInput).getAttribute("value").contains(this.returnDate);
    }

    public Object findInputs() {
        find(departureDateInput).getText();
        find(returnDateInput).getText();
        return null;
    }

    public void clickFindTicketBtn(){

        click(findCheapTicketBtn);
    }
}



