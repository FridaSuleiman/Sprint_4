package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    // Константа: базовый URL сайта
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Заголовок главной страницы
    private final By homeHeader = By.className("Home_Header__iJKdX");

    // Кнопка "Заказать" в верхней части страницы
    private final By upOrderButton = By.className("Button_Button__ra12g");

    // Кнопка "Заказать" в нижней части страницы
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Заголовок раздела "Вопросы о важном"
    private final By questionsHeader = By.className("Home_FourPart__1uthg");

    // Кнопка для проверки статуса заказа
    private final By orderState = By.xpath(".//button[text()='Статус заказа']");

    // Поле для ввода номера заказа
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");

    // Кнопка "Go!" для подтверждения ввода номера заказа
    private final By buttonGo = By.xpath(".//button[text()='Go!']");

    // Кнопка "Яндекс" для перехода на главную страницу Яндекс
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");

    // Кнопка согласия на использование куки
    private final By cookiesButton = By.className("App_CookieButton__3cvqF");

    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");


    // Элементы FAQ

    // Сколько стоит аренда самоката?
    private final By priceQuestion = By.id("accordion__heading-0");
    private final By priceAnswer = By.id("accordion__panel-0");

    // Можно ли заказать несколько самокатов сразу?
    private final By multipleScootersQuestion = By.id("accordion__heading-1");
    private final By multipleScootersAnswer = By.id("accordion__panel-1");

    // Как рассчитывается время аренды?
    private final By rentalTimeQuestion = By.id("accordion__heading-2");
    private final By rentalTimeAnswer = By.id("accordion__panel-2");

    // Можно ли выбрать дату доставки заранее?
    private final By deliveryDateQuestion = By.id("accordion__heading-3");
    private final By deliveryDateAnswer = By.id("accordion__panel-3");

    // Можно ли продлить заказ или изменить его?
    private final By extendOrderQuestion = By.id("accordion__heading-4");
    private final By extendOrderAnswer = By.id("accordion__panel-4");

    // Нужно ли заряжать самокат во время аренды?
    private final By chargingQuestion = By.id("accordion__heading-5");
    private final By chargingAnswer = By.id("accordion__panel-5");

    // Можно ли отменить заказ после оформления?
    private final By cancelOrderQuestion = By.id("accordion__heading-6");
    private final By cancelOrderAnswer = By.id("accordion__panel-6");

    // Осуществляется ли доставка за МКАД?
    private final By deliveryOutsideMkadQuestion = By.id("accordion__heading-7");
    private final By deliveryOutsideMkadAnswer = By.id("accordion__panel-7");


    // Тексты ответов на вопросы
    private final String textPriceAnswer = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private final String textMultipleScootersAnswer = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private final String textRentalTimeAnswer = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private final String textDeliveryDateAnswer = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private final String textExtendOrderAnswer = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private final String textChargingAnswer = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private final String textCancelOrderAnswer = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private final String textDeliveryOutsideMkadAnswer = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(homeHeader));
        return this;
    }

    // Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                (driver.findElement(accordionLabel).getText() != null && !driver.findElement(accordionLabel).getText().isEmpty()));
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    // Метод прокрутки ко второй кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }

    public void clickCreateOrderButton(String button) {
        if ("up".equalsIgnoreCase(button)) {
            clickUpOrderButton();
        } else if ("down".equalsIgnoreCase(button)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    public HomePage clickOrderState() {
        driver.findElement(orderState).click();
        return this;
    }

    public HomePage inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(numberOrder))
                .sendKeys(number);
        return this;
    }

    public HomePage clickGo() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonGo))
                .click();
        return this;
    }

    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }

    public HomePage clickCookiesButton() {
        driver.findElement(cookiesButton).click();
        return this;
    }

    public HomePage checkOrderStatus(String number) {
        waitForLoadHomePage();
        clickOrderState();
        inputOrderNumber(number);
        clickGo();
        return this;
    }

    public HomePage openOrderForm() {
        clickUpOrderButton();
        return this;
    }

    public HomePage clickScooterAndReturnToMain() {
        driver.findElement(scooterButton).click();
        return this;
    }

    // Геттеры текстов ответов
    public String getTextPriceAnswer() {
        return textPriceAnswer;
    }

    public String getTextMultipleScootersAnswer() {
        return textMultipleScootersAnswer;
    }

    public String getTextRentalTimeAnswer() {
        return textRentalTimeAnswer;
    }

    public String getTextDeliveryDateAnswer() {
        return textDeliveryDateAnswer;
    }

    public String getTextExtendOrderAnswer() {
        return textExtendOrderAnswer;
    }

    public String getTextChargingAnswer() {
        return textChargingAnswer;
    }

    public String getTextCancelOrderAnswer() {
        return textCancelOrderAnswer;
    }

    public String getTextDeliveryOutsideMkadAnswer() {
        return textDeliveryOutsideMkadAnswer;
    }

    public HomePage openQuestion(String questionName) {
        By questionLocator = null;
        switch (questionName) {
            case "price": questionLocator = priceQuestion; break;
            case "multipleScooters": questionLocator = multipleScootersQuestion; break;
            case "rentalTime": questionLocator = rentalTimeQuestion; break;
            case "deliveryDate": questionLocator = deliveryDateQuestion; break;
            case "extendOrder": questionLocator = extendOrderQuestion; break;
            case "charging": questionLocator = chargingQuestion; break;
            case "cancelOrder": questionLocator = cancelOrderQuestion; break;
            case "deliveryOutsideMkad": questionLocator = deliveryOutsideMkadQuestion; break;
        }

        if (questionLocator != null) {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(questionLocator))
                    .click();
        }
        return this;
    }

    public String getAnswerText(String answerName) {
        By answerLocator = null;
        switch (answerName) {
            case "price": answerLocator = priceAnswer; break;
            case "multipleScooters": answerLocator = multipleScootersAnswer; break;
            case "rentalTime": answerLocator = rentalTimeAnswer; break;
            case "deliveryDate": answerLocator = deliveryDateAnswer; break;
            case "extendOrder": answerLocator = extendOrderAnswer; break;
            case "charging": answerLocator = chargingAnswer; break;
            case "cancelOrder": answerLocator = cancelOrderAnswer; break;
            case "deliveryOutsideMkad": answerLocator = deliveryOutsideMkadAnswer; break;
        }

        if (answerLocator != null) {
            waitLoadAfterClickQuestion(answerLocator);
            return driver.findElement(answerLocator).getText();
        }
        return "";
    }
}
