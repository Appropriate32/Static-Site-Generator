package com.ssg.parser;

import java.util.ArrayList;
import java.io.File;

public class MainTest {
    public static void main(String[] args) {

        String testMarkdown = FileManager.readMarkdownFile(
                "/home/eissa/uni-repos/OOP-2nd-Semester/OOP-Lab-Project/static-site-generator/test.md");

        String fragments = MarkdownParser.parse(testMarkdown);

        String wrappedHtml = DocumentBuilder.htmlWrapper(fragments, "Test Site");
        FileManager.saveHtmlFile(wrappedHtml,
                "/mnt/c/Users/DELL/Downloads/index.html");
    }
}
