package com.ssg.parser;

import java.util.ArrayList;
import java.io.File;

public class MainTest {
    public static void main(String[] args) {

        String testMarkdown = "# Welcome to the Java SSG\n" +
                "\n" +
                "This is a standard paragraph. The parser should ignore the blank line above this.\n" +
                "\n" +
                "## Subheadings Work Too\n" +
                "Another paragraph here to test sequential lines.\n" +
                "\n" +
                "###### Deepest Heading Level\n" +
                "####### This has 7 hashes, so it should be a paragraph!\n" +
                "  This line has leading spaces, which should be trimmed by your cleanLine logic.  ";

        String generatedHtml = MarkdownParser.parse(testMarkdown);

        String wrappedHtml = DocumentBuilder.htmlWrapper(generatedHtml, "Test Site");
        System.out.println(wrappedHtml);
    }
}
