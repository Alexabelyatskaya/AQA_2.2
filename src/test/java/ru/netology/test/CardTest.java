package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {
    private Selenide p;

    @Test
    void shouldRegisterByAccountNumber() throws InterruptedException {
        open("http://localhost:9999");

        $("[placeholder='Город']").setValue("Майкоп");
        $("[placeholder='Дата встречи']").setValue("15.01.2022");
        $("[name='name']").setValue("Иванова Татьяна");
        $("[name='phone']").setValue("+78008008080");
        $("[data-test-id='agreement']").click();
        $$(".button__text").find(exactText("Забронировать")).click();
        System.out.println("1");
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
