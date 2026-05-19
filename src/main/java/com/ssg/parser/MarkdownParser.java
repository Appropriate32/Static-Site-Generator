package com.ssg.parser;

import java.util.ArrayList;

public class MarkdownParser {
    public static String parse(String rawMarkdown) { // Takes a raw markdown string
        ArrayList<HTMLNode> documentNodes = new ArrayList<>();
        String[] lines = rawMarkdown.split("\\R");

        for (String line : lines) { // Polymorphic generation of nodes based on Markdown syntax
            String cleanLine = line.trim();
            if (cleanLine.isEmpty()) {
                continue;
            }

            if (cleanLine.startsWith("> ")) {
                documentNodes.add(new BlockQuoteNode(cleanLine.substring(2).trim()));
            } else if (cleanLine.startsWith("- ") || cleanLine.startsWith("* ")) {
                documentNodes.add(new ListItemNode(cleanLine.substring(2).trim()));
            } else if (cleanLine.startsWith("#")) {
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
                documentNodes.add(new ParagraphNode(cleanLine));
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
