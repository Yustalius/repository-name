package ru.bulgakov.webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WSLoginPage;
import ru.bulgakov.webshop.pages.WSWelcomePage;
import ru.bulgakov.webshop.pages.WsRegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.*;

public class LoginTest  extends TestBase {
  private static final Faker faker = new Faker();
  private String email;
  private String password;

  @Nested
  public class PositiveTests {

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
    @Tag("UI")
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
  }

  @ParameterizedTest(name = "Авторизация с невалидным email: {0}")
  @CsvFileSource(resources = "/email.csv")
  void invalidEmailLoginTest(String email) {
    open(WEB_SHOP_LOGIN_URL, WSLoginPage.class)
        .enterEmail(email)
        .enterPassword("password")
        .verifyEmailValidationErrorAppear()
        .submitLogin();
  }
}
