package com.github.dwilliamsgs.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.dwilliamsgs.utils.data.Parameter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AllureEnvironmentUtil {

  public static final String DEFAULT_ALLURE_DIRECTORY = "target/allure-results/";
  public static final String DEFAULT_ALLURE_FILENAME = "environment.xml";

  /**
   * Creates an Allure Environment XML file required to populate the Environment widget within an Allure report.
   * This XML file is incrementally written to using the provided HashMap of keys / values to be included within the file.
   * Once finished this file is created within {@link #DEFAULT_ALLURE_DIRECTORY} under the filename {@link #DEFAULT_ALLURE_FILENAME}
   * @param parameters HashMap of key / values to be included within the file
   */
  public static void writeAllureEnvironmentXml(HashMap<String, String> parameters) {
    writeAllureEnvironmentXml(parameters, DEFAULT_ALLURE_DIRECTORY);
  }

  /**
   * Creates an Allure Environment XML file required to populate the Environment widget within an Allure report.
   * This XML file is incrementally written to using the provided HashMap of keys / values to be included within the file.
   * Once finished this file is created within the provided customAllureDirectory using the filename {@link #DEFAULT_ALLURE_FILENAME}
   * @param parameters            HashMap of key / values to be included within the file
   * @param customAllureDirectory Custom directory to story the environment.xml file
   */
  public static void writeAllureEnvironmentXml(HashMap<String, String> parameters, String customAllureDirectory) {
    createAllureDirectory(customAllureDirectory);

    try {
      XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
      XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(
              Files.newOutputStream(Paths.get(customAllureDirectory + DEFAULT_ALLURE_FILENAME)), "utf-8");
      XmlMapper xmlMapper = new XmlMapper();

      streamWriter.writeStartDocument();
      streamWriter.writeStartElement("environment");

      for (Map.Entry<String, String> entry : parameters.entrySet()) {
        Parameter newParameter = new Parameter();
        newParameter.setKey(entry.getKey());
        newParameter.setValue(entry.getValue());
        xmlMapper.writeValue(streamWriter, newParameter);
      }

      streamWriter.writeEndElement();
      streamWriter.writeEndDocument();
      streamWriter.close();

    } catch (XMLStreamException | IOException e) {
      e.printStackTrace();
    }

    Logging.getLogger().info("Written parameters to Allure Environment Xml file at directory: [{}]",
            customAllureDirectory + DEFAULT_ALLURE_FILENAME);
  }

  /**
   * Checks to see whether the {@link #DEFAULT_ALLURE_DIRECTORY} exists and creates it if not
   */
  private static void createAllureDirectory(String customAllureDirectory) {
    File allureResultsDir = new File(customAllureDirectory);
    if (!allureResultsDir.exists()) {
      boolean directoryCreated = allureResultsDir.mkdir();
      Logging.getLogger().info("Created Allure Directory: [{}]", directoryCreated);
    }
  }
}