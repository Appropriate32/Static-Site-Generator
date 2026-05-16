package com.ssg.parser;

import java.util.ArrayList;

public class MarkdownParser {
    public static String parse(String rawMarkdown) { // Takes a raw markdown string
        ArrayList<HTMLNode> documentNodes = new ArrayList<>();
        String[] lines = rawMarkdown.split("\\R");
        for (String line : lines) {
            String cleanLine = line.trim();
            if (cleanLine.isEmpty()) {
                continue;
            }
            if (cleanLine.startsWith("#")) {
                int hashCount = 0;
                for (int i = 0; i < cleanLine.length(); i++) {
                    char current = cleanLine.charAt(i);

                    if (current != '#') {
                        break;
                    }

                    hashCount++;
                }
                if (hashCount <= 6) {
                    documentNodes.add(new HeadingNode(cleanLine.substring(hashCount).trim(), hashCount));
                } else {
                    documentNodes.add(new ParagraphNode(cleanLine));
                }

            } else {
                documentNodes.add(new ParagraphNode(cleanLine.trim()));
            }

        }

        StringBuilder finalHtml = new StringBuilder();

        for (HTMLNode node : documentNodes) {
            finalHtml.append(node.generateTag());
            finalHtml.append("\n");
        }

        return finalHtml.toString();
    }
}
