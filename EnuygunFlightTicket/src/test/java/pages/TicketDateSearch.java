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
    By nextMonthBtn = By.xpath("(//div[@role='button'])[2]");
    By monthAndYearOnCalendar = By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
    By findCheapTicketBtn = By.xpath("//*[text()='Ucuz bilet bul']");
    SimpleDateFormat firstFormat = new SimpleDateFormat("MMMM yyyy");
    SimpleDateFormat secondFormat = new SimpleDateFormat("d MMM yyyy");
    SimpleDateFormat onlyDayFormat = new SimpleDateFormat("d");
    Calendar c1 = Calendar.getInstance();
    public String departureDay;
    public String returnDay;
    public String departureDate;
    public String returnDate;


    public TicketDateSearch(WebDriver driver) {

        super(driver);
    }


    public void selectDepartureDay() {
        // Gets today's date.
        c1.setTime(new Date());
        // It calculates the new date by adding the number of days entered the "departureDay" variable to today's date.
        c1.add(Calendar.DATE, Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
        String departureMonthAndYearValue = firstFormat.format(c1.getTime());
        this.departureDay = onlyDayFormat.format(c1.getTime());
        this.departureDate = secondFormat.format(c1.getTime());

        click(departureDateInput);

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthAndYearOnCalendar));
            String departureMonthAndYearText = find(monthAndYearOnCalendar).getText();

            if (departureMonthAndYearText.equals(departureMonthAndYearValue)) {
                WebElement departureDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.departureDay + "']"));
                departureDay.click();
                break;
            } else {
                click(nextMonthBtn);
            }
        }
    }

    public boolean getDepartureDay() {
       return find(departureDateInput).getAttribute("value").contains(this.departureDate);
    }

    public void selectReturnDay() {

        c1.setTime(new Date());
        c1.add(Calendar.DATE, (Integer.parseInt(DriverSetup.properties.getProperty("departureDay"))) + (Integer.parseInt(DriverSetup.properties.getProperty("returnDay"))));
        String returnMonthAndYearValue = firstFormat.format(c1.getTime());
        this.returnDay = onlyDayFormat.format(c1.getTime());
        this.returnDate = secondFormat.format(c1.getTime());

        click(oneWayBtn);

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthAndYearOnCalendar));
            String returnMonthAndYearText = find(monthAndYearOnCalendar).getText();

            if (returnMonthAndYearText.equals(returnMonthAndYearValue)) {
                WebElement returnDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.returnDay + "']"));
                returnDay.click();
                break;
            } else {
                click(nextMonthBtn);
            }
        }
    }

    public boolean getReturnDay() {
        return find(returnDateInput).getAttribute("value").contains(this.returnDate);
    }

    public Object findInputs() {
        getText(departureDateInput);
        getText(returnDateInput);
        return null;
    }

    public void clickFindTicketBtn(){
        click(findCheapTicketBtn);
    }
}



