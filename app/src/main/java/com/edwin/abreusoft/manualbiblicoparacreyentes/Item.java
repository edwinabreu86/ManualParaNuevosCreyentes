package com.edwin.abreusoft.manualbiblicoparacreyentes;

class Item {
    private final String text1;
    private final String text2;
    private String text3;
    private String text4;
    private final boolean bookmarked;

    Item(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.bookmarked = false;
    }

    Item(String text1, String text2, String text3) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.bookmarked = false;
    }

    Item(String text1, String text2, String text3, String text4) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.bookmarked = false;
    }

    String getText1() {
        return text1;
    }

    String getText2() {
        return text2;
    }

    String getText3() {
        return text3;
    }

    String getText4() {
        return text4;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }
}
