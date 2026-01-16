package package1;

public class Example {
  public String publicField;              // Доступен всем
  protected static String protectedField;        // Доступен наследникам и классам в том же пакете
  String defaultField;                    // Доступен только в том же пакете (без ключевого слова)
  private String privateField;            // Доступен только в этом классе
}
