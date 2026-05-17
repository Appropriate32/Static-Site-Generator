package com.ssg.parser;

public class BlockQuoteNode extends HTMLNode {

    public BlockQuoteNode(String content) {
        super(content);
    }

    @Override
    public String generateTag() {
        return String.format("<blockquote>%s</blockquote>", getContent());
    }

}
