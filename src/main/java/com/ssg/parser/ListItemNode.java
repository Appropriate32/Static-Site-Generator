package com.ssg.parser;

public class ListItemNode extends HTMLNode {
    public ListItemNode(String content) {
        super(content);
    }

    @Override
    public String generateTag() {
        return String.format("<ul>\n <li>%s</li>\n</ul>", getContent());
    }
}
