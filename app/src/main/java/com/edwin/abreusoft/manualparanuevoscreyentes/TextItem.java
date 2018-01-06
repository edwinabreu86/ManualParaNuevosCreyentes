package com.edwin.abreusoft.manualparanuevoscreyentes;

public class TextItem {
    private final String text1;
    private final String text2;

    TextItem(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
