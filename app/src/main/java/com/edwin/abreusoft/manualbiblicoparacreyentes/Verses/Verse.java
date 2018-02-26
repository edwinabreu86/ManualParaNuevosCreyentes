package com.edwin.abreusoft.manualbiblicoparacreyentes.Verses;

public class Verse {

    private final String book;
    private final String verse;
    private final String content;

    public Verse(String book, String verse, String content) {
        this.book = book;
        this.verse = verse;
        this.content = content;
    }

    public String getBook() {
        return book;
    }

    public String getVerse() {
        return verse;
    }

    public String getContent() {
        return content;
    }
}
