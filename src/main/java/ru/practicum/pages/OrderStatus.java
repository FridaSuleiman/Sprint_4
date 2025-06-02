package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderStatus {


    // Локатор сообщения "Заказ не найден"
    private final By notFound = By.xpath(".//*[@alt='Not found']");

    // Локатор кнопки "Самокат"
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");

    private WebDriver driver;

    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    public OrderStatus waitLoadOrderStatusPade() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(notFound));
        return this;
    }

    public HomePage clickScooterAndReturnToMain() {
        clickScooter();
        return new HomePage(driver);
    }

    public OrderStatus clickScooter() {
        driver.findElement(scooterButton).click();
        return this;
    }

    public OrderStatus checkOrderNotFoundMessage() {
        Assert.assertTrue("Сообщение 'Заказ не найден' не отображается",
                driver.findElement(notFound).isDisplayed());
        return this;
    }
}
