package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поле имя
    private final By firstName = By.xpath("//input[@placeholder = '* Имя']");
    //Поле фамилия
    private final By lastName = By.xpath("//input[@placeholder = '* Фамилия']");
    //Поле адреса
    private final By address = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Выбор метро
    private final By metro = By.className("select-search__input");
    //Раскрытый список метро
    private final By metroOption = By.className("select-search__option");
    //Поле телефона
    private final By phone = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private final By nextButton = By.xpath("//button[text() = 'Далее']");

    public void fillPersonalData(String name, String surname, String addressText, String metroStation, String phoneNumber) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(address).sendKeys(addressText);

        driver.findElement(metro).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(metroOption));

        driver.findElements(metroOption).stream()
                .filter(e -> e.getText().contains(metroStation))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Станция метро не найдена: " + metroStation))
                .click();

        driver.findElement(phone).sendKeys(phoneNumber);
        driver.findElement(nextButton).click();
    }
}