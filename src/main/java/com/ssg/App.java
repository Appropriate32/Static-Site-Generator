package com.ssg;

import com.ssg.parser.DocumentBuilder;
import com.ssg.parser.MarkdownParser;
import com.ssg.parser.FileManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Root Layout & Overall Styling
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #282c34;"); // Dark background color (One Dark theme)

        // 2. Header Area
        Label titleLabel = new Label("Markdown SSG Engine");
        titleLabel.setStyle(
                "-fx-text-fill: #abb2bf; -fx-font-size: 18px; -fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; -fx-font-weight: bold;");

        VBox header = new VBox(titleLabel);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setAlignment(Pos.CENTER_LEFT);
        root.setTop(header);

        // 3. The Interactive Center (SplitPane)
        //
        SplitPane splitPane = new SplitPane();
        splitPane.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        // --- Left: Editor ---
        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(0, 10, 0, 20));
        Label mdLabel = new Label("MARKDOWN");
        mdLabel.setStyle("-fx-text-fill: #e06c75; -fx-font-weight: bold; -fx-font-size: 24px;");

        TextArea markdownInput = new TextArea();
        markdownInput.setPromptText("# Type Markdown here...");

        markdownInput.setStyle(
                "-fx-control-inner-background: #21252b; -fx-text-fill: #abb2bf; -fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 15px; -fx-border-color: #3e4451; -fx-border-radius: 5; -fx-background-radius: 5;");
        VBox.setVgrow(markdownInput, javafx.scene.layout.Priority.ALWAYS);
        leftBox.getChildren().addAll(mdLabel, markdownInput);

        // --- Right: Preview ---
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(0, 20, 0, 10));
        Label htmlLabel = new Label("HTML OUTPUT");
        htmlLabel.setStyle("-fx-text-fill: #98c379; -fx-font-weight: bold; -fx-font-size: 24px;");

        TextArea htmlOutput = new TextArea();
        htmlOutput.setEditable(false);
        htmlOutput.setStyle(
                "-fx-control-inner-background: #21252b; -fx-text-fill: #d19a66; -fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 15px; -fx-border-color: #3e4451; -fx-border-radius: 5; -fx-background-radius: 5;");
        VBox.setVgrow(htmlOutput, javafx.scene.layout.Priority.ALWAYS);
        rightBox.getChildren().addAll(htmlLabel, htmlOutput);

        splitPane.getItems().addAll(leftBox, rightBox);
        splitPane.setDividerPositions(0.5); // Start with a 50/50 split
        root.setCenter(splitPane);

        // 4. Footer Area (Action Bar)
        Button loadBtn = new Button("LOAD .MD");
        Button generateBtn = new Button("GENERATE HTML");
        Button exportBtn = new Button("EXPORT .HTML");

        // A quick helper string for styling so we don't copy-paste it three times
        String btnStyle = "-fx-background-color: #61afef; -fx-text-fill: #282c34; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;";
        loadBtn.setStyle(btnStyle);
        generateBtn.setStyle(btnStyle);
        exportBtn.setStyle(btnStyle);

        // HBox with a 20px gap between buttons
        HBox footer = new HBox(20, loadBtn, generateBtn, exportBtn);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20));
        root.setBottom(footer);

        loadBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Markdown File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Markdown Files", "*.md"));

            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                String fileContent = FileManager.readMarkdownFile(selectedFile.getAbsolutePath());
                markdownInput.setText(fileContent);
            }
        });

        generateBtn.setOnAction(event -> {
            String rawText = markdownInput.getText();
            String fragments = MarkdownParser.parse(rawText);
            String finalWebpage = DocumentBuilder.htmlWrapper(fragments, "My SSG Project");
            htmlOutput.setText(finalWebpage);
        });

        exportBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save HTML File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));

            File fileToSave = fileChooser.showSaveDialog(stage);

            if (fileToSave != null) {
                String finalPath = fileToSave.getAbsolutePath();

                if (!finalPath.endsWith(".html")) {
                    finalPath += ".html";
                }
                boolean success = FileManager.saveHtmlFile(htmlOutput.getText(), finalPath);
                if (success) {
                    System.out.println("Webpage exported successfully to: " + finalPath);
                }
            }
        });

        // 6. Scene Setup
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Java-Based SSG Utility");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}