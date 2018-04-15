package com.edwin.abreusoft.manualbiblicoparacreyentes.Objects;

public class Verse {
    private final String book;
    private final String verse;
    private final String content;
    private final boolean favorite;

    public Verse(String book, String verse, String content, boolean favorite) {
        this.book = book;
        this.verse = verse;
        this.content = content;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }
}
