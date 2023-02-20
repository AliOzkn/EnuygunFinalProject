package pages.Locators;

import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface TicketDate {

    By departureDateInput = By.xpath("//input[@name='DepartureDate']");
    By returnDateInput = By.xpath("//input[@name='ReturnDate']");
    By oneWayBtn = By.xpath("(//input[@type='checkbox'])[1]");
    By nextMonthBtn = By.xpath("(//div[@role='button'])[2]");
    By monthAndYearOnCalendar = By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
    SimpleDateFormat monthAndYearFormat = new SimpleDateFormat("MMMM yyyy");
    SimpleDateFormat fullFormat = new SimpleDateFormat("d MMM yyyy");
    SimpleDateFormat onlyDayFormat = new SimpleDateFormat("d");
    Calendar c1 = Calendar.getInstance();

}
