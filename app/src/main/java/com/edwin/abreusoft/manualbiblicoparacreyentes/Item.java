package com.edwin.abreusoft.manualbiblicoparacreyentes;

public class Item {
    private final String text1;
    private final String text2;
    private String text3;
    private String text4;
    private boolean favorite;

    Item(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    Item(String text1, String text2, String text3, boolean favorite) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.favorite = favorite;
    }

    Item(String text1, String text2, String text3, String text4) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
