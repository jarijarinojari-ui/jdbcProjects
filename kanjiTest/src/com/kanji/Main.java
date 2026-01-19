package com.kanji;

import com.kanji.service.QuizService;

public class Main {
    public static void main (String[] args) {
        QuizService service = new QuizService();
        service.startProgram();
    }
}
