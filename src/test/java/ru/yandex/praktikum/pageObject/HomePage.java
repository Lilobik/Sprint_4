package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    // Кнопка принять куки
    private static final By COOKIE = By.id("rcc-confirm-button");
    // Список "Вопросы о важном"
    private static final String ACCORDION_QUESTIONS = "accordion__heading-";
    // Список "Вопросы о важном"
    private static final String ACCORDION_ANSWERS = ".//div[@id='accordion__panel-%d']/p";
    // "Заказать" хидер
    private static final By HEAD_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g']");
    // "Заказать" центр
    private static final By MID_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Принять куки
    public void clickCookie() {
        driver.findElement(COOKIE).click();
    }

    // Проверка списка вопросов-ответов
    public void clickQuestion(int item) {
        driver.findElement(By.id(ACCORDION_QUESTIONS + item)).click();
    }
    public String getAnswer(int item) {
        WebElement accordionItem = new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ACCORDION_ANSWERS, item))));
        String answerText = accordionItem.getText();
        return answerText;
    }
    //Значения списка вопросов-ответов
    public static final String[] QUESTIONS = new String[] {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я жизу за МКАДом, привезёте?"
    };
    public static final String[] ANSWERS = new String[] {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };
    //2 кнопки Заказать
    public void homeOrderButton(String orderButton) {
        if (orderButton.equals("headerButton")) {
            driver.findElement(HEAD_ORDER_BUTTON).click();
        } else if (orderButton.equals("middleButton")) {
            driver.findElement(MID_ORDER_BUTTON).click();
        }
    }
}