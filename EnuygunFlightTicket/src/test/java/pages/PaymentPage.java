package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {
    WebElement proceedToPaymentBtn = driver.findElement(By.cssSelector(".btn.btn-success"));

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProceedToPaymentBtnEnabled() {
        scrollToElement(driver, proceedToPaymentBtn);
        proceedToPaymentBtn.isEnabled();
        return true;
    }
}
