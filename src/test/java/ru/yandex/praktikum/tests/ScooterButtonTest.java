package tests;
import org.junit.Test;
import ru.practicum.pages.AboutRenter;
import ru.practicum.pages.HomePage;
import ru.practicum.pages.OrderStatus;
import ru.yandex.praktikum.tests.CommonTest;
import static org.junit.Assert.assertEquals;

public class ScooterButtonTest extends CommonTest {

    @Test
    public void clickScooterFromAboutRenterPageTest() {
        new HomePage(driver).openOrderForm()
                .clickScooterAndReturnToMain();
        assertEquals(HomePage.BASE_URL, driver.getCurrentUrl().trim());
    }

    @Test
    public void clickScooterFromAboutScooterPageTest() {
        new HomePage(driver).openOrderForm()
                .clickCookiesButton();

        new AboutRenter(driver).fillAboutRenterForm("Имя", "Фамилия", "Адрес", 77, "+79999999999")
                .goToAboutScooter()
                .clickScooterAndReturnToMain();

        assertEquals(HomePage.BASE_URL, driver.getCurrentUrl().trim());
    }

    @Test
    public void clickScooterFromOrderStatusPageTest() {
        new HomePage(driver).clickCookiesButton()
                .checkOrderStatus("12345");

        new OrderStatus(driver).clickScooterAndReturnToMain();

        assertEquals(HomePage.BASE_URL, driver.getCurrentUrl().trim());
    }
}