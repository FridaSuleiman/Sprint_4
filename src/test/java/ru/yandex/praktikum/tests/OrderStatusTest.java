package tests;

import org.junit.Test;
import ru.practicum.pages.HomePage;
import ru.practicum.pages.OrderStatus;
import ru.yandex.praktikum.tests.CommonTest;

public class OrderStatusTest extends CommonTest {

    private final String numberOrder = "12345";

    @Test
    public void orderStatusWithoutNumberTest() {
        new HomePage(driver)
                .checkOrderStatus(numberOrder);

        new OrderStatus(driver)
                .checkOrderNotFoundMessage();
    }
}