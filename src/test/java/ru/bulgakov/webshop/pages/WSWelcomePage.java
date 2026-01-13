package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WSWelcomePage {
  private final SelenideElement registerLink = $("a.ico-register");
  private final SelenideElement loginLink = $(".ico-login");
  private final SelenideElement userEmailInHeader = $$("div.header-links ul li a").get(0);

  public WsRegistrationPage openRegistration() {
    registerLink.click();
    return new WsRegistrationPage();
  }

  public WSLoginPage openLogin() {
    loginLink.click();
    return new WSLoginPage();
  }

  public WSWelcomePage checkUserLoggedIn(String email) {
    userEmailInHeader.shouldHave(text(email));
    return this;
  }
}
