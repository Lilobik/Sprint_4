package ru.yandex.praktikum.scenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.HomePage;
import ru.yandex.praktikum.pageObject.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private final String orderButton;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    public OrderTest(String orderButton, String name, String lastName, String address, String metro, String phone, String date, String period, String color, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] orderTestData() {
        return new Object[][] {
                {"middleButton", "Виталик", "Пушистых", "Малый Ивановский переулок, 7-9с1", "Китай-город", "+79221111111", "9.05.2023", "сутки", "чёрный жемчуг", "Тест комментария"},
                {"headerButton", "Тест", "Тестович", "площадь Киевского Вокзала, 2", "Киевская", "89321111111", "8.05.2023", "пятеро суток", "серая безысходность", "Тест 123"},
        };
    }
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @Test
    public void orderTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCookie();
        homePage.homeOrderButton(orderButton);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillName(name);
        orderPage.fillLastName(lastName);
        orderPage.fillAddress(address);
        orderPage.fillMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNextButton();
        orderPage.fillDate(date);
        orderPage.selectPeriod(period);
        orderPage.selectColor(color);
        orderPage.fillComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.getOrderModalHeader();
        boolean modalWindow = orderPage.getOrderModalHeader();
        assertTrue(modalWindow);
    }
    @After
    public void closeUp() {
        driver.quit();
    }
}
