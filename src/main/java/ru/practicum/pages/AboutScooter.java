package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutScooter {
    // Заголовок страницы заказа
    private final By rentHeader = By.className("Order_Header__BZXOb");
    // Поле для выбора даты привоза самоката
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле для выбора длительности аренды
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
    // Выбор цвета — черный
    private final By colourBlack = By.id("black");
    // Выбор цвета — серый
    private final By colourGrey = By.id("grey");
    // Поле для комментария курьеру
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка создания заказа
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка "Яндекс" для перехода на главную страницу Яндекс
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");
    // Кнопка "Самокат" для перехода на главную страницу ЯндексСамокат
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");
    WebDriver driver;

    public AboutScooter(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    public AboutScooter inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        By durationOption = By.xpath("//div[@class='Dropdown-option' and text()='" + newDuration + "']");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(durationOption))
                .click();

        return this;
    }

    public AboutScooter changeColour(String colour) {
        if ("BLACK".equalsIgnoreCase(colour)) {
            driver.findElement(colourBlack).click();
        } else if ("GREY".equalsIgnoreCase(colour)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public AboutScooter fillAboutScooterForm(String date, String duration, String colour, String comment) {
        waitAboutRentHeader();
        inputDate(date);
        inputDuration(duration);
        changeColour(colour);
        inputComment(comment);
        clickButtonCreateOrder();
        return this;
    }

    public HomePage clickScooterAndReturnToMain() {
        driver.findElement(scooterButton).click();
        return new HomePage(driver);
    }

    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
    }

    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }
}
