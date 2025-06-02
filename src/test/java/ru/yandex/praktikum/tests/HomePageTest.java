package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import ru.practicum.pages.HomePage;


@RunWith(Parameterized.class)
public class HomePageTest extends CommonTest {

    private final String questionName;
    private final String expected;

    public HomePageTest(String questionName, String expected) {
        this.questionName = questionName;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"price", new HomePage(null).getTextPriceAnswer()},
                {"multipleScooters", new HomePage(null).getTextMultipleScootersAnswer()},
                {"rentalTime", new HomePage(null).getTextRentalTimeAnswer()},
                {"deliveryDate", new HomePage(null).getTextDeliveryDateAnswer()},
                {"extendOrder", new HomePage(null).getTextExtendOrderAnswer()},
                {"charging", new HomePage(null).getTextChargingAnswer()},
                {"cancelOrder", new HomePage(null).getTextCancelOrderAnswer()},
                {"deliveryOutsideMkad", new HomePage(null).getTextDeliveryOutsideMkadAnswer()}
        };
    }

    @Test
    public void checkQuestionsTest() {
        HomePage homePage = new HomePage(driver)
                .waitForLoadHomePage()
                .scrollToQuestions()
                .openQuestion(questionName);

        String result = homePage.getAnswerText(questionName);

        assertEquals(expected, result);
    }
}