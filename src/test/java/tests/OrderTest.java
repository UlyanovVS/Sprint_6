package tests;

import base.BaseTest;
import data.OrderData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageobject.MainPage;
import pageobject.OrderPage;
import pageobject.RentPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    static Stream<OrderData> orderData() {
        return Stream.of(
                new OrderData("Иван", "Иванов", "Москва", "Сокольники", "88888888888", "31.07.2025", "двое суток", false),
                new OrderData("Петр", "Петров", "Питер", "Лубянка", "+77777777777", "01.08.2025", "сутки", true)
        );
    }

    @ParameterizedTest
    @MethodSource("orderData")
    void makeOrderTest(OrderData data) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();

        if (data.fromLowerButton) {
            mainPage.clickLowerOrderButton();
        } else {
            mainPage.clickUpperOrderButton();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillPersonalData(data.name, data.surname, data.address, data.metro, data.phone);

        RentPage rentPage = new RentPage(driver);
        rentPage.fillRentData(data.date);
        rentPage.fillRentDuration(data.duration);
        rentPage.confirmOrder();
        assertTrue(rentPage.isOrderConfirmed(), "Окно с подтверждением заказа не появилось.");
    }
}
