package ru.bulgakov.webshop.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class JenkinsTest {

  @Test
  @Tag("jenkins")
  void jenkinsCheck() {
    System.out.println("Jenkins запускается!");
  }
}
