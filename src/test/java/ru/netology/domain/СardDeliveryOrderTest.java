package ru.netology.domain;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class СardDeliveryOrderTest {
    private final String city = DataGenerator.getCity();
    private final String name = DataGenerator.getName();
    private final String phone = DataGenerator.getPhone();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSendValidRequest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue(city);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.getDate(3));
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 10000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.getDate(3)));
    }

    @Test
    void shouldSendFormWithAnotherDate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue(city);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.getDate(3));
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 10000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.getDate(3)));

        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.getDate(5));
        $$("[type='button']").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .button__text").shouldHave(text("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.getDate(5)));
    }
}

