package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import ru.practicum.pages.HomePage;
import static ru.yandex.praktikum.tests.Constants.*;

@RunWith(Parameterized.class)
public class HomePageTest extends CommonTest {

    private final String questionName;
    private final String expected;

    public HomePageTest(String questionName, String expected) {
        this.questionName = questionName;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}") // Добавляем читаемые имена тестов
    public static Object[][] getParameters() {
        return new Object[][]{
                {"price", PRICE_ANSWER},
                {"multipleScooters", MULTIPLE_SCOOTERS_ANSWER},
                {"rentalTime", RENTAL_TIME_ANSWER},
                {"deliveryDate", DELIVERY_DATE_ANSWER},
                {"extendOrder", EXTEND_ORDER_ANSWER},
                {"charging", CHARGING_ANSWER},
                {"cancelOrder", CANCEL_ORDER_ANSWER},
                {"deliveryOutsideMkad", DELIVERY_OUTSIDE_MKAD_ANSWER}
        };
    }

    @Test
    public void checkQuestionsTest() {
        HomePage homePage = new HomePage(driver)
                .waitForLoadHomePage()
                .scrollToQuestions()
                .openQuestion(questionName);

        String actual = homePage.getAnswerText(questionName);
        assertEquals("Неверный текст ответа для вопроса: " + questionName,
                expected,
                actual);
    }
}