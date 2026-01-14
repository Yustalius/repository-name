package ru.bulgakov.webshop.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WSWelcomePage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class RegistrationTest extends TestBase {
  private static final Faker faker = new Faker();

  @BeforeAll
  static void before() {
    Configuration.browserSize = "1920x1080";
  }

  @Test
  @Owner("v.yustus")
  @Tags({@Tag("UI"), @Tag("positive")})
  @Severity(CRITICAL)
  @Epic("Авторизация")
  @Feature("Регистрация")
  @Story("Регистрация нового пользователя")
  @Link("TASK-120")
  @DisplayName("Успешная регистрация нового пользователя")
  @Description("Создаем нового пользователя со случайными данными через интерфейс")
  void registrationTest() {
    String email = faker.internet().emailAddress();
    String password = faker.harryPotter().character() + faker.number().positive();

    open(WEB_SHOP_URL, WSWelcomePage.class)
        .openRegistration()
        .verifyRegistrationOpened()
        .selectMaleGender()
        .enterFirstName(faker.name().firstName())
        .enterLastName(faker.name().lastName())
        .enterEmail(email)
        .enterPassword(password)
        .confirmPassword(password)
        .submitRegistration()
        .checkRegistrationCompleted()
        .checkUserLoggedIn(email);
  }
}
