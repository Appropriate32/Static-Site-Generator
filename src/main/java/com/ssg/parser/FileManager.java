package com.ssg.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static final String DEFAULT_CSS = "body { font-family: sans-serif; line-height: 1.6; max-width: 800px; margin: 40px auto; padding: 20px; color: #333; }\n"
            +
            "h1, h2 { color: #111; border-bottom: 1px solid #eee; padding-bottom: 10px; }\n" +
            "blockquote { border-left: 4px solid #61afef; padding-left: 15px; color: #555; font-style: italic; }\n" +
            "p { margin-bottom: 15px; }";

    public static String readMarkdownFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return "Error: Could not load the Markdown file.";
        }
    }

    public static boolean saveHtmlFile(String htmlContent, String outputPath) {
        try {
            Files.writeString(Path.of(outputPath), htmlContent);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }
}
