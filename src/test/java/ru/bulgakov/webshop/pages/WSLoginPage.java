package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WSLoginPage {

  private final SelenideElement pageTitle = $("div.page-title h1");
  private final SelenideElement emailInput = $("input#Email");
  private final SelenideElement passwordInput = $("input#Password");
  private final SelenideElement rememberMeCheckbox = $("input#RememberMe");
  private final SelenideElement loginButton = $("input.login-button");

  public WSLoginPage checkLoginPageOpened() {
    pageTitle.shouldHave(text("Welcome, Please Sign In!"));
    return this;
  }

  @Step("Ввести электронную почту {email}")
  public WSLoginPage enterEmail(String email) {
    emailInput.setValue(email);
    return this;
  }

  @Step("Ввести пароль {password}")
  public WSLoginPage enterPassword(String password) {
    passwordInput.setValue(password);
    return this;
  }

  public WSLoginPage checkRememberMe() {
    rememberMeCheckbox.click();
    return this;
  }

  @Step("Подтвердить авторизацию")
  public WSWelcomePage submitLogin() {
    loginButton.click();
    return new WSWelcomePage();
  }

  @Step("Проверить, что появилось сообщение с ошибкой валидации почты")
  public WSLoginPage verifyEmailValidationErrorAppear() {
    $("span.field-validation-error").shouldBe(visible);
    return this;
  }

  public WSWelcomePage login(String email, String password, boolean rememberMe) {
    enterEmail(email)
        .enterPassword(password);

    if (rememberMe) {
      checkRememberMe();
    }

    return submitLogin();
  }
}
