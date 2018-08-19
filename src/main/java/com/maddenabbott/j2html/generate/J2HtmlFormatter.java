package com.maddenabbott.j2html.generate;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class J2HtmlFormatter {
  private final Formatter formatter = new Formatter();

  public String format(String code) {
    String wrapperStart = "public class Wrapper {\n  public void wrapper() {\n";
    String wrapperEnd = ";\n  }\n}\n";
    try {
      String formatted = formatter.formatSource(wrapperStart + code + wrapperEnd);
      return formatted
          .substring(wrapperStart.length(), formatted.length() - wrapperEnd.length())
          .replace("\n        ", "\n")
          .replace("    ", "  ")
          .substring(2);
    } catch (FormatterException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
