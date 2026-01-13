package ru.bulgakov.webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WSWelcomePage;
import ru.bulgakov.webshop.pages.WsRegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class LoginTest  extends TestBase {
  private static final Faker faker = new Faker();
  private String email;
  private String password;

  @BeforeEach
  void beforeEach() {
    email = faker.internet().emailAddress();
    password = faker.harryPotter().character() + faker.number().positive();

    open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
        .register(
            faker.name().firstName(),
            faker.name().lastName(),
            email,
            password)
        .checkUserLoggedIn(email);

    clearBrowserCookies();
    clearBrowserLocalStorage();
  }

  @Test
  void successLoginTest() {
    open(WEB_SHOP_URL, WSWelcomePage.class)
        .openLogin()
        .checkLoginPageOpened()
        .enterEmail(email)
        .enterPassword(password)
        .checkRememberMe()
        .submitLogin()
        .checkUserLoggedIn(email);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/email.csv")
  void invalidEmailLoginTest(String email) {
    open(WEB_SHOP_URL, WSWelcomePage.class)
        .openLogin()
        .enterEmail(email)
        .enterPassword(password)
        .verifyEmailValidationErrorAppear()
        .submitLogin();
  }
}
