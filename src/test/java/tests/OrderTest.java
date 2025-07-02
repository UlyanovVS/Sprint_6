package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import pageobject.RentPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    static Stream<OrderData> orderData() {
        return Stream.of(
                new OrderData("Иван", "Иванов", "Москва", "Сокольники", "88888888888", "31.07.2025", "двое суток"),
                new OrderData("Петр", "Петров", "Питер", "Лубянка", "+77777777777", "01.08.2025", "сутки")
        );
    }

    @ParameterizedTest
    @MethodSource("orderData")
    void makeOrderTest(OrderData data) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickUpperOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillPersonalData(data.name, data.surname, data.address, data.metro, data.phone);

        RentPage rentPage = new RentPage(driver);
        rentPage.fillRentData(data.date);
        rentPage.fillRentDuration(data.duration);
        rentPage.confirmOrder();
        assertTrue(rentPage.isOrderConfirmed(), "Окно с подтверждением заказа не появилось.");
    }

    static class OrderData {
        String name, surname, address, metro, phone, date, duration;

        OrderData(String name, String surname, String address, String metro, String phone, String date, String duration) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metro = metro;
            this.phone = phone;
            this.date = date;
            this.duration = duration;
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
