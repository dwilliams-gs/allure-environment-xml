package com.github.dwilliamsgs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

public class Logging {

  /**
   * SLF4J logging engine used within the project
   * @return logger to be used to output information
   */
  public static Logger getLogger() {
    return LoggerFactory.getLogger(Util.getCallingClass());
  }
}