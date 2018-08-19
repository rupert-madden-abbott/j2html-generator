package com.maddenabbott.j2html.generate;

import java.util.List;

import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;

public class J2HtmlGenerator {
  public String generate(String html) {
    StringBuilder buffer = new StringBuilder();
    parse(html).forEach(node -> NodeTraversor.filter(new J2HtmlNodeFilter(buffer), node));
    return buffer.toString();
  }

  private List<Node> parse(String html) {
    return Parser.parseFragment(html, null, "");
  }
}
