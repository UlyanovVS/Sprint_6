package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поле даты
    private final By dateInput = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    //Выбранный день в календаре
    private final By selectedDate = By.className("react-datepicker__day--selected");
    //Выбор срока аренды
    private final By durationSelect = By.className("Dropdown-root");
    //Кнопка заказать
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text() = 'Заказать']");
    //Кнопка подтверждения
    private final By confirmYesButton = By.xpath("//button[text() = 'Да']");
    //Заголовок окна успешного заказа
    private final By orderConfirmedModal = By.xpath("//div[contains(@class,'Order_ModalHeader') and contains(text(),'Заказ оформлен')]");


    public void fillRentData(String date) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(selectedDate).click();
    }

    public void fillRentDuration(String duration) {
        driver.findElement(durationSelect).click();
        By durationOption = By.xpath(String.format("//div[@class='Dropdown-option'] [text()='%s']", duration));
        driver.findElement(durationOption).click();
    }

    public void confirmOrder() {
        driver.findElement(orderButton).click();
        driver.findElement(confirmYesButton).click();
    }

    public boolean isOrderConfirmed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(orderConfirmedModal, "Заказ оформлен"));
    }
}