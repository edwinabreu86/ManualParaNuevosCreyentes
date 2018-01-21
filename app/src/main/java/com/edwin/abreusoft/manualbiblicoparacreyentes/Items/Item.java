package com.edwin.abreusoft.manualbiblicoparacreyentes.Items;

public class Item {
    private final String question;
    private final String answer;

    public Item(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;

    }
}
