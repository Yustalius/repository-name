package ru.bulgakov.webshop.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class API {

  @Test
  @Tags({@Tag("API"), @Tag("negative")})
  void mockAPITest() {
    System.out.println("API negative test");
  }
}
