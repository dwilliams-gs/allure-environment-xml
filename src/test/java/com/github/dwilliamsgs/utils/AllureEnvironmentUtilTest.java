package com.github.dwilliamsgs.utils;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static com.github.dwilliamsgs.utils.AllureEnvironmentUtil.writeAllureEnvironmentXml;
import static org.testng.AssertJUnit.assertTrue;

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
    assertTrue(new File(AllureEnvironmentUtil.DEFAULT_ALLURE_DIRECTORY + AllureEnvironmentUtil.DEFAULT_ALLURE_FILENAME).isFile());
  }

  @Test
  public void hasCustomAllureEnvironmentXmlBeenCreated() {
    String customAllureDirectory = "target/allure-results-custom/";
    writeAllureEnvironmentXml(parameters, customAllureDirectory);
    assertTrue(new File(customAllureDirectory + AllureEnvironmentUtil.DEFAULT_ALLURE_FILENAME).isFile());
  }
}