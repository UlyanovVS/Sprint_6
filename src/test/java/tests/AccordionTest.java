package tests;

import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccordionTest extends BaseTest {

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

}
