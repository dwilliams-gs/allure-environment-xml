# allure-environment-xml
Java library to enable writing to an XML file to populate an Allure report environment widget. 

- Create a new HashMap of parameters
- Pass that HashMap into the `writeAllureEnvironmentXml` which then saves those keys / values into an XML file to be consumed by the Allure report environment widget.
- Ability to customise the Allure directory

For more information on the Allure report environment widget - https://docs.qameta.io/allure/#_environment

## Example Usage

```java
import static com.github.dwilliamsgs.utils.AllureEnvironmentUtil.*;

public class AllureEnvironmentUtilTest {

  public HashMap<String, String> parameters;

  @BeforeSuite
  void generateAllureEnvironmentXml() {
    parameters = new HashMap<>();
    parameters.put("Environment", "Production");
    parameters.put("Browser", "Chrome");
    parameters.put("Version", "98.0.4758.102");
    parameters.put("Url", "https://uk.gymshark.com/");
    parameters.put("Scope", "Smoke");
  }

  @Test
  public void hasAllureEnvironmentXmlBeenCreated() {
    writeAllureEnvironmentXml(parameters);
    assertTrue(new File(DEFAULT_ALLURE_DIRECTORY + DEFAULT_ALLURE_FILENAME).isFile());
  }

  @Test
  public void hasCustomAllureEnvironmentXmlBeenCreated() {
    String customAllureDirectory = "target/allure-results-custom/";
    writeAllureEnvironmentXml(parameters, customAllureDirectory);
    assertTrue(new File(customAllureDirectory + DEFAULT_ALLURE_FILENAME).isFile());
  }
}
```

## Allure Report

<img width="600" alt="AllureReport" src="https://user-images.githubusercontent.com/89386048/156541891-762eea44-1739-4e14-8efc-0c9d21e6674f.png">

## Dependency

```text
<dependency>
  <groupId>com.github.dwilliamsgs</groupId>
  <artifactId>allure-environment-xml</artifactId>
  <version>1.0.0</version>
</dependency>
```