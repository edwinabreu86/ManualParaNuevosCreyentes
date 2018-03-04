package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.Verse;

public class Book extends Verse {
    private final String text4;

    public Book(String text1, String text2, String text3, String text4) {
        super(text1, text2, text3);
        this.text4 = text4;
    }

    public String getText4() {
        return text4;
    }
}
