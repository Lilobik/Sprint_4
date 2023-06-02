package ru.yandex.praktikum.scenario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.praktikum.pageObject.HomePage;
import static ru.yandex.praktikum.pageObject.HomePage.QUESTIONS;
import static ru.yandex.praktikum.pageObject.HomePage.ANSWERS;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccordionTest {
    private WebDriver driver;
    private final String question;
    private final String expectedAnswer;
    private final int accordionItem;
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    public AccordionTest(String question, String expectedAnswer, int accordionItem) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.accordionItem = accordionItem;
    }
    @Parameterized.Parameters
    public static Object[][] getAccordionData() {

        Object[][] objAccordion = new Object[QUESTIONS.length][3];
        for (int i = 0; i < QUESTIONS.length; i++) {
            objAccordion[i][0] = QUESTIONS[i];
            objAccordion[i][1] = ANSWERS[i];
            objAccordion[i][2] = i;
        }
        return  objAccordion;
    }
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @Test
    public void checkAccordionText() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickCookie();
        objHomePage.clickQuestion(accordionItem);
        String answer = objHomePage.getAnswer(accordionItem);
        assertEquals("Текст не совпадает: " + question, expectedAnswer, answer);
    }
    @After
    public void cleanUp() {
        driver.quit();
    }
}