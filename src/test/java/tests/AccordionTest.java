package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccordionTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @ParameterizedTest
    @CsvSource({
            "0, Сутки — 400 рублей",
            "1, один заказ — один самокат",
            "2, Отсчёт времени аренды",
            "3, Только начиная с завтрашнего дня",
            "4, Пока что нет",
            "5, Самокат приезжает к вам с полной зарядкой",
            "6, Да, пока самокат не привезли",
            "7, Да, обязательно"
    })
    public void checkAccordionAnswer(int index, String expected) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        mainPage.scrollToFAQ();
        mainPage.clickAccordionQuestion(index);
        String actual = mainPage.getAccordionAnswerText(index);
        assertTrue(actual.contains(expected));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
