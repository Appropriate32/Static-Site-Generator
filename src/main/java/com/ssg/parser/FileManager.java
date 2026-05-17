package com.ssg.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

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
