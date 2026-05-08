package com.ssg.parser;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ArrayList<HTMLNode> nodes = new ArrayList<>();
        nodes.add(new HeadingNode("This is a heading", 1));
        nodes.add(new ParagraphNode("This is a paragraph"));
        StringBuilder nodeString = new StringBuilder();
        for (HTMLNode node : nodes) {
            nodeString.append(node.generateTag());
            nodeString.append("\n");

        }

        System.out.println(nodeString);
    }
}
