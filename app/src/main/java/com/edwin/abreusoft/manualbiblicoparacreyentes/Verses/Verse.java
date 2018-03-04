package com.edwin.abreusoft.manualbiblicoparacreyentes.Verses;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.Item;

public class Verse extends Item {

    private final String text3;

    public Verse(String text1, String text2, String text3) {
        super(text1, text2);
        this.text3 = text3;
    }

    public String getText3() {
        return text3;
    }
}
