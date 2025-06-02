package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.practicum.pages.HomePage;


public class CommonTest {

    protected WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(HomePage.BASE_URL);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}