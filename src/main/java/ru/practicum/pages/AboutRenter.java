package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutRenter {

    // Заголовок страницы заказа
    private final By orderHeader = By.className("Order_Header__BZXOb");
    // Поле для ввода имени
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    // Поле для ввода фамилии
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле для ввода адреса доставки
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле для выбора станции метро
    private final By stateMetro = By.className("select-search__input");
    // Поле для ввода номера телефона
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее" для перехода к следующему шагу
    By buttonNext = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and normalize-space()='Далее']");
    // Шаблон XPath для выбора конкретного элемента метро по значению
    private final String nameStateMetro = ".//button[@value='%s']";

    // Кнопка "Самокат" для перехода на главную страницу ЯндексСамокат
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");
    WebDriver driver;

    public AboutRenter(WebDriver driver) {
        this.driver = driver;
    }

    // - Метод ожидания загруки страницы заказа
    public AboutRenter waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
        return this;
    }

    public AboutRenter inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    public AboutRenter inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
        return this;
    }

    public AboutRenter inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    public AboutRenter changeStateMetro(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    public AboutRenter inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(telephone));
        driver.findElement(telephone).sendKeys(newTelephone);
        return this;
    }

    public AboutRenter fillAboutRenterForm(String name, String surname, String address, int metroNumber, String phone) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        changeStateMetro(metroNumber);
        inputTelephone(phone);
        return this;
    }

    public HomePage clickScooterAndReturnToMain() {
        clickScooter();
        return new HomePage(driver);
    }

    public void clickNextButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(buttonNext))
                .click();
    }

    public AboutScooter goToAboutScooter() {
        clickNextButton();
        return new AboutScooter(driver);
    }



    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }

}