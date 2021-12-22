package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardTest {

    @Test
    void shouldRegisterByAccountNumber() {
        open("http://localhost:9999");

        Configuration.holdBrowserOpen = true;

        $("[placeholder='Город']").setValue("Майкоп");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[name='name']").setValue("Иванова Татьяна");
        $("[name='phone']").setValue("+78008008080");
        $("[data-test-id='agreement']").click();
        $$(".button__text").find(exactText("Забронировать")).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}