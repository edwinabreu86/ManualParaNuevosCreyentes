package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

public class Book  {
    private final String title;
    private final String author;
    private final String chapters;
    private final String description;

    public Book(String title, String author, String chapters, String description) {
        this.title = title;
        this.author = author;
        this.chapters = chapters;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    String getChapters() {
        return chapters;
    }

    String getDescription() {
        return description;
    }
}
