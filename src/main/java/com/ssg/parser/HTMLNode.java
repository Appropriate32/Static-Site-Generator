package com.ssg.parser;

// Base abstract class for all Markdown elements
public abstract class HTMLNode {
    private String content;

    public HTMLNode(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public abstract String generateTag();
}
