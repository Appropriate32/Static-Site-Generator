package com.ssg.parser;

public class MainTest {
    public static void main(String[] args) {
        HeadingNode h1 = new HeadingNode("Hello", 1);
        System.out.println(h1.generateTag());
    }
}
