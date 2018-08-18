package com.maddenabbott.j2html.generate;

import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;

public class J2HtmlGenerator {
  public String generate(String html) {
    StringBuilder buffer = new StringBuilder();
    parse(html).childNodes().forEach(node -> NodeTraversor.filter(new J2HtmlNodeFilter(buffer), node));
    return buffer.toString();
  }

  private Document parse(String html) {
    return Parser.xmlParser().parseInput(html, "");
  }
}
