package com.edwin.abreusoft.manualparanuevoscreyentes;

public class BookItem {

    private String name;
    private String authors;
    private int chapters;
    private String meaning;

    public BookItem(String name, String authors, int chapters, String meaning) {
        this.name = name;
        this.authors = authors;
        this.chapters = chapters;
        this.meaning = meaning;
    }

    public String getName() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public int getChapters() {
        return chapters;
    }

    public String getMeaning() {
        return meaning;
    }
}
