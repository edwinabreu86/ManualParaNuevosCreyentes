package com.edwin.abreusoft.manualbiblicoparacreyentes.Objects;

public class Book {
    private final String book;
    private final String author;
    private final String chapters;
    private final String content;

    public Book(String book, String author, String chapters, String content) {
        this.book = book;
        this.author = author;
        this.chapters = chapters;
        this.content = content;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getChapters() {
        return chapters;
    }

    public String getContent() {
        return content;
    }
}
