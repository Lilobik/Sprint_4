package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    //Поле имя
    private static final String NAME_INPUT = ".//input[@placeholder='* Имя']";
    //Поле фамилия
    private static final String LASTNAME_INPUT = ".//input[@placeholder='* Фамилия']";
    //Поле адрес
    private static final String ADDRESS_INPUT = ".//input[@placeholder='* Адрес: куда привезти заказ']";
    //Поле метро
    private static final String METRO_INPUT = ".//input[@placeholder='* Станция метро']";
    //Поле телефон
    private static final String PHONE_INPUT = ".//input[@placeholder='* Телефон: на него позвонит курьер']";
    //Кнопка далее
    private static final String NEXT_BUTTON = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    //Поле даты
    private static final String DATA_INPUT = ".//input[@placeholder='* Когда привезти самокат']";
    //Срок аренды
    private final static String RENTAL_PERIOD = ".//div[text()='* Срок аренды']";
    //Список срока аренды
    private final static String RENTAL_PERIOD_LIST = ".//div[@class='Dropdown-root is-open']//div[text()='%s']";
    //Цвет - чёрный жемчуг
    private static final By COLOR_BLACK = By.id("black");
    //Цвет - серая безысходность
    private static final By COLOR_GRAY = By.id("grey");
    //Поле комментарий для курьера
    private final static String COMMENT_INPUT = ".//input[@placeholder='Комментарий для курьера']";
    //Кнопка Заказать
    private static final String ORDER_BUTTON = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    // Кнопка Да
    private static final String YES_BUTTON = ".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
    // Заголовок окна Заказ оформлен
    private static final String ORDER_MODAL_HEADER = ".//div[text()='Заказ оформлен']";
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    private WebDriver driver;
    public void fillName(String name){
        driver.findElement(By.xpath(NAME_INPUT)).sendKeys(name);
    }
    public void fillLastName(String lastName){
        driver.findElement(By.xpath(LASTNAME_INPUT)).sendKeys(lastName);
    }
    public void fillAddress(String address){
        driver.findElement(By.xpath(ADDRESS_INPUT)).sendKeys(address);
    }
    public void fillMetro(String metro) {
        WebElement fieldMetro = driver.findElement(By.xpath(METRO_INPUT));
        fieldMetro.click();
        fieldMetro.sendKeys(metro);
        fieldMetro.sendKeys(Keys.DOWN,Keys.ENTER);
    }
    public void fillPhone(String phone){
        driver.findElement(By.xpath(PHONE_INPUT)).sendKeys(phone);
    }
    public void clickNextButton(){
        driver.findElement(By.xpath(NEXT_BUTTON)).click();
    }
    public void fillDate(String date){
        WebElement fieldDate = new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(DATA_INPUT)));
        fieldDate.sendKeys(date);
        fieldDate.sendKeys(Keys.ENTER);
    }
    public void selectPeriod(String period) {
        WebElement fieldPeriod = driver.findElement(By.xpath(RENTAL_PERIOD));
        fieldPeriod.click();
        String locatorSelectListPeriod = String.format(RENTAL_PERIOD_LIST, period);
        WebElement selectPeriod = new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorSelectListPeriod)));
        selectPeriod.click();
    }
    public void selectColor(String color) {
            if (color.equals("серая безысходность")) {
                driver.findElement(COLOR_GRAY).click();
            } else if (color.equals("чёрный жемчуг")) {
                driver.findElement(COLOR_BLACK).click();
            }
    }
    public void fillComment(String comment){
        driver.findElement(By.xpath(COMMENT_INPUT)).sendKeys(comment);
    }
    public void clickOrderButton(){
        driver.findElement(By.xpath(ORDER_BUTTON)).click();
    }
    public void clickYesButton(){
        driver.findElement(By.xpath(YES_BUTTON)).click();
    }
    //Проверка поп-ап об успехе заказа
    public boolean getOrderModalHeader(){
        WebElement modalWindow = new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(ORDER_MODAL_HEADER)));
        return modalWindow.isDisplayed();
    }
}
