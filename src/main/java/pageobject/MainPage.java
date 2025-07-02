package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Подтверждение кук
    private final By cookieButton = By.id("rcc-confirm-button");
    //Кнопка заказать верхняя
    private final By upperOrderButton = By.xpath("(//button[text() = 'Заказать'])[1]");
    //Кнопка заказать нижняя
    private final By lowerOrderButton = By.xpath("(//button[text() = 'Заказать'])[2]");
    //Список вопросов
    private final By accordion = By.className("accordion");

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(cookieButton))
                .click();
    }

    public void scrollToFAQ() {
        WebElement faq = driver.findElement(accordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faq);
    }

    public void clickAccordionQuestion(int index) {
        driver.findElement(By.id("accordion__heading-" + index)).click();
    }

    public String getAccordionAnswerText(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();
    }

    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }
}