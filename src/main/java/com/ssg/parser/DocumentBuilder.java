package com.ssg.parser;

public class DocumentBuilder {
    public static String htmlWrapper(String bodyHtml, String pageTitle) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("<meta charset=\"UTF-8\">\n")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("<title>").append(pageTitle).append("</title>\n") // Injects the custom title!
                .append("<link rel=\"stylesheet\" href=\"style.css\">\n")
                .append("</head>\n")
                .append("<body>\n")
                .append(bodyHtml).append("\n")
                .append("</body>\n")
                .append("</html>");

        return html.toString();
    }
}
