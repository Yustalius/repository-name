package ru.bulgakov.webshop.test;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WSWelcomePage;

import static com.codeborne.selenide.Selenide.open;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class RegistrationTest extends TestBase {
  private static final Faker faker = new Faker();

  @BeforeAll
  static void before() {
    Configuration.browserSize = "1920x1080";
  }

  @Test
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
