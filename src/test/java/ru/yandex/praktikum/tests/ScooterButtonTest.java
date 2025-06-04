package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.practicum.pages.AboutRenter;
import ru.practicum.pages.HomePage;
import ru.practicum.pages.OrderStatus;
import ru.yandex.praktikum.tests.CommonTest;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ScooterButtonTest extends CommonTest {

    private HomePage homePage;
    private AboutRenter aboutRenter;
    private OrderStatus orderStatus;

    @Before
    public void setUp() {
        homePage = new HomePage(driver);
        aboutRenter = new AboutRenter(driver);
        orderStatus = new OrderStatus(driver);
        homePage.clickCookiesButton(); // Единообразная обработка cookies
    }

    @Test
    public void clickScooterFromAboutRenterPageTest() {
        homePage.openOrderForm()
                .clickScooterAndReturnToMain();
        verifyReturnToMainPage();
    }

    @Test
    public void clickScooterFromAboutScooterPageTest() {
        homePage.openOrderForm();

        aboutRenter.fillAboutRenterForm("Имя", "Фамилия", "Адрес", 77, "+79999999999")
                .goToAboutScooter()
                .clickScooterAndReturnToMain();

        verifyReturnToMainPage();
    }

    @Test
    public void clickScooterFromOrderStatusPageTest() {
        homePage.checkOrderStatus("12345");

        orderStatus.clickScooterAndReturnToMain();

        verifyReturnToMainPage();
    }

    private void verifyReturnToMainPage() {
        assertEquals("Не произошел возврат на главную страницу",
                HomePage.BASE_URL,
                driver.getCurrentUrl().trim());
    }
}