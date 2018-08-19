package com.maddenabbott.j2html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.maddenabbott.j2html.generate.J2HtmlFormatter;
import com.maddenabbott.j2html.generate.J2HtmlGenerator;

public class Application {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Missing required argument: Path to HTML file");
      System.exit(1);
    }

    String code = new J2HtmlGenerator().generate(readFile(args[0]));

    try {
      System.out.println(new J2HtmlFormatter().format(code));
    } catch (IllegalArgumentException e) {
      System.err.println("Unable to format code. Here is the unformatted result.");
      System.out.println(code);
    }
  }

  private static String readFile(String path) {
    try {
      return new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
