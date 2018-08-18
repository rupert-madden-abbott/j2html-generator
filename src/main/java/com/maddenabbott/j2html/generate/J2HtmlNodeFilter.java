package com.maddenabbott.j2html.generate;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;

import static java.util.stream.Collectors.joining;
import static org.jsoup.select.NodeFilter.FilterResult.CONTINUE;

class J2HtmlNodeFilter implements NodeFilter {
  private final StringBuilder buffer;

  private int previousDepth = -1;

  J2HtmlNodeFilter(final StringBuilder buffer) {
    this.buffer = buffer;
  }

  @Override
  public FilterResult head(final Node node, final int depth) {
    if (!(node instanceof TextNode || node instanceof Element)) {
      return CONTINUE;
    }

    if (node instanceof TextNode && ((TextNode) node).text().trim().isEmpty()) {
      return CONTINUE;
    }

    if (depth <= previousDepth) {
      buffer.append(",");
    }
    previousDepth = depth;

    if (node instanceof Element) {
      return head((Element) node);
    } else {
      return head((TextNode) node);
    }
  }

  @Override
  public FilterResult tail(final Node node, final int depth) {
    if (node instanceof Element) {
      return tail((Element) node);
    }

    return CONTINUE;
  }

  private FilterResult head(TextNode textNode) {
    buffer.append("\"")
        .append(textNode.text())
        .append("\"");
    return CONTINUE;
  }

  private FilterResult head(Element element) {
    buffer.append(element.tagName())
        .append("(");

    return CONTINUE;
  }

  private FilterResult tail(Element element) {
    buffer.append(")")
        .append(handle(element.attributes()));

    return CONTINUE;
  }

  private String handle(final Attributes attributes) {
    return attributes.asList().stream().map(this::handle).collect(joining());
  }

  private String handle(final Attribute attribute) {
    return ".attr(\"" + attribute.getKey() + "\", \"" + attribute.getValue() + "\")";
  }
}
