package com.ssg.parser;

public class HeadingNode extends HTMLNode {
    private int level;

    public HeadingNode(String content, int level) {
        super(content);
        this.level = level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String generateTag() {
        return String.format("<h%d>%s</h%d>", getLevel(), getContent(), getLevel());
    }

}
