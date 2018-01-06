package com.edwin.abreusoft.manualparanuevoscreyentes;

public class BookItem {

    private final String name;
    private final String authors;
    private final int chapters;
    private final String meaning;

    BookItem(String name, String authors, int chapters, String meaning) {
        this.name = name;
        this.authors = authors;
        this.chapters = chapters;
        this.meaning = meaning;
    }

    public String getName() {
        return name;
    }

    String getAuthors() {
        return authors;
    }

    int getChapters() {
        return chapters;
    }

    String getMeaning() {
        return meaning;
    }
}
