package tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

import ru.practicum.pages.AboutRenter;
import ru.practicum.pages.AboutScooter;
import ru.practicum.pages.HomePage;
import ru.practicum.pages.PopUpWindow;
import ru.yandex.praktikum.tests.CommonTest;


@RunWith(Parameterized.class)
public class OrderCreateTest extends CommonTest {

    // Заказ самоката.

    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final String button;

    public OrderCreateTest(String button,
                           String name,
                           String surname,
                           String address,
                           int stateMetroNumber,
                           String telephoneNumber,
                           String date,
                           String duration,
                           String colour,
                           String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {"up", "Адам", "Клуни", "Центр Москвы", 3, "79608412911", "05.07.2025", "шестеро суток", "GREY", "comments one"},
                {"up", "Бетси", "Оулсен", "Проспект Просвещения 10", 7, "79972745459", "22.07.2025", "пятеро суток", "BLACK", "comments two"},
                {"up", "Макс", "Джонсон", "Улица Свободы 19", 10, "79821926308", "20.06.2023", "сутки", "BLACK", "comments three"},
                {"down", "Юля", "Питт", "Станция Лебедей", 123, "79297137817", "07.09.2018", "шестеро суток", "GREY", "comments one"},
                {"down", "Оля", "Соломонова", "Африка", 7, "79963018797", "27.10.2025", "пятеро суток", "BLACK", "comments two"},
                {"down", "Эля", "Белова", "За углом", 10, "79209999999", "05.11.1985", "сутки", "BLACK", "comments three"}
        };
    }

    @Test
    public void createOrderWithUpButtonTest() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookiesButton()
                .clickCreateOrderButton(button);

        new AboutRenter(driver)
                .fillAboutRenterForm(name, surname, address, stateMetroNumber, telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .fillAboutScooterForm(date, duration, colour, comment);

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}