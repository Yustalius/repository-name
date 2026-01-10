package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.bulgakov.pages.YandexSearchPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QaTest {

  @Test
  void mentoringPriceShouldBe47000Test() {
    Configuration.pageLoadTimeout = 100000; // таймаут прогрузки страницы
    Configuration.timeout = 100000; // таймаут прогрузки элементов

    open("https://ya.ru/", YandexSearchPage.class)
        .search("bulgakov qa")
        .submit()
        .closeDefaultBrowserSelectWindow()
        .openLink("ivanbulgakovqa.ru")
        .clickPrice();

    // создаем класс под страницу ->
    // выписываем методы для взаимодествия ->
    // вытаскиваем из теста действия ->
    // выносим локаторы в переменны

    sleep(3000);
    switchTo().window(1);
    $$(".t-menu__list li").last().click(); // welcome страница обучения
    $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click(); // xpath
    $(byText("Бегу оплачивать")).click();

    switchTo().window(2);
    $(".styles_price__2lruq").$("h2").shouldHave(text("₽ 47 000 ")); // страница оплаты
  }
}
