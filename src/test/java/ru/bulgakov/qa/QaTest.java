package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QaTest {

  @Test
  void mentoringPriceShouldBe47000Test() {
    /*
    * Тест-кейс - проверить, что предоплата по обучению - 47000 рублей
    * 1. открыть поисковик (Яндекс)
    * 2. ввести данные сайта (bulgakov qa)
    * 3. нажать кнопку поиск
    * 4. в поисковой выдаче найти нужный сайт, кликнуть на него
    * 5. нажать на кнопку "Стоимость"
    * 6. нажать на кнопку "Хочу вкатиться в QA"
    * 7. нажать на кнопку "Бегу оплачивать"
    * 8. проверить, что к оплате 47 000 рублей
    * */
    Configuration.pageLoadTimeout = 100000; // таймаут прогрузки страницы
    Configuration.timeout = 100000; // таймаут прогрузки элементов

    open("https://ya.ru/");
    $("#text").setValue("bulgakov qa");
    $("[type=submit]").click();
    $(".DistributionButtonClose").click();
    $(byText("ivanbulgakovqa.ru")).click();

    sleep(3000);
    switchTo().window(1);
    $$(".t-menu__list li").last().click(); // xpath
    $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
    $(byText("Бегу оплачивать")).click();

    switchTo().window(2);
    $(".styles_price__2lruq").$("h2").shouldHave(text("₽ 47 000 "));
  }

  @Test
  void findRepositoryTest() {

  }
}
