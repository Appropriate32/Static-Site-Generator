package com.ssg.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static final String DEFAULT_CSS = "body { font-family: 'Segoe UI', system-ui, sans-serif; line-height: 1.6; max-width: 800px; margin: 40px auto; padding: 30px; color: #2c3e50; background-color: #f8f9fa; box-shadow: 0 4px 6px rgba(0,0,0,0.1); border-radius: 8px; }\n"
            +
            "h1 { color: #1a252f; border-bottom: 2px solid #3498db; padding-bottom: 10px; }\n" +
            "h2, h3 { color: #2c3e50; }\n" +
            "blockquote { border-left: 5px solid #3498db; background-color: #ebf5fb; padding: 15px 20px; margin: 20px 0; color: #555; font-style: italic; border-radius: 0 5px 5px 0; }\n"
            +
            "ul { background-color: #ffffff; padding: 20px 40px; border-radius: 5px; border: 1px solid #e1e8ed; }\n" +
            "li { margin-bottom: 10px; }\n" +
            "p { margin-bottom: 15px; font-size: 16px; }";

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
