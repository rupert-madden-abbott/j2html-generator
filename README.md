# j2html Generator

A generator for [j2html](https://github.com/tipsy/j2html).

Convert any HTML fragment into valid j2html code.

## Requirements

* Java 8+
* j2html 1.3.0+

## Usage

### Command Line

1. Checkout this repository.
2. Build with Maven `mvn package`
3. Execute jar `java -jar target\app.jar PATH_TO_HTML_FILE`

Result will be output to stdout.

### Library

    String result = new J2HtmlGenerator().generate("<p id=\"hello\">Hello, World!</p>");

Output:

    p("Hello, World!").attr("id", "hello")
    
The output can also be formatted:

    String formattedCode = new J2HtmlFormatter().format(code);
