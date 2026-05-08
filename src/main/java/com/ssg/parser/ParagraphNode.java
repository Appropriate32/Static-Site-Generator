package com.ssg.parser;

public class ParagraphNode extends HTMLNode {
    public ParagraphNode(String content) {
        super(content);
    }

    @Override
    public String generateTag() {

        return String.format("<p>%s</p>", getContent());
    }

}
