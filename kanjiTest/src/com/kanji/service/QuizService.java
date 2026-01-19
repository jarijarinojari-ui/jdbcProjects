package com.kanji.service;

import com.kanji.dao.KanjiDAO;
import com.kanji.model.Kanji;

import java.util.List;
import java.util.Scanner;

public class QuizService {

    private KanjiDAO dao = new KanjiDAO();
    private Scanner sc = new Scanner(System.in);

    public void startProgram() {
        while (true) {
            System.out. println("=====일본어 한자 퀴즈 프로그램=====");
            System.out.println("1. 퀴즈 풀기");
            System.out.println("2. 새 단어 등록");
            System.out.println("3. 프로그램 종료");
            System.out.println("메뉴 선택 >>");

            int choice = sc.nextInt();

            if (choice == 1) {
                solveQuiz(); // 퀴즈 시작!
            } else if (choice == 2) {
                registerWord(); // 단어 등록!
            } else if (choice == 3) {
                System.out.println("프로그램을 종료합니다. 사요나라! 👋");
                break; // 반복문 탈출 (종료)
            } else {
                System.out.println("⚠️ 잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private void solveQuiz() {
        // (1) 테마 목록 보여주기
        System.out.println("\n[ 주제를 선택하세요 ]");
        List<String> themeList = dao.getThemeList();

        if (themeList.isEmpty()) {
            System.out.println("⚠️ 등록된 문제가 없습니다. 먼저 단어를 등록해주세요!");
            return;
        }

        for (int i = 0; i < themeList.size(); i++) {
            System.out.println((i + 1) + ". " + themeList.get(i));
        }

        System.out.print("번호 선택 >> ");
        int themeIndex = sc.nextInt();
        String selectedTheme = themeList.get(themeIndex - 1); // 사용자가 고른 테마 이름

        // (2) 해당 테마의 문제 가져오기 (DAO 호출)
        List<Kanji> quizList = dao.getQuizList(selectedTheme);

        int score = 0;
        int totalQuestions = quizList.size();

        System.out.println("\n🚀 [" + selectedTheme + "] 퀴즈를 시작합니다! (총 " + totalQuestions + "문제)");

        // (3) 문제 출제 반복문
        for (Kanji q : quizList) {
            System.out.println("------------------------------------------------");
            System.out.println("문제: " + q.getKanji()); // 한자 보여줌 (예: 愛)

            // [문제 1] 요미가나 맞추기
            System.out.print("답(요미가나) 입력 : ");
            String inputYomi = sc.next();

            if (inputYomi.equals(q.getYomigana())) {
                System.out.println("⭕ 정답입니다!");
                score++;
            } else {
                System.out.println("❌ 땡! 정답은 '" + q.getYomigana() + "' 입니다.");
            }

            // [문제 2] 뜻 맞추기 (보너스 문제처럼 활용 가능)
            System.out.print("답(뜻/한국어) 입력 : ");
            String inputMean = sc.next();

            if (inputMean.equals(q.getKread())) { // Kread는 한국어 뜻
                System.out.println("⭕ 정답입니다!");
                score++;
            } else {
                System.out.println("❌ 땡! 정답은 '" + q.getKread() + "' 입니다.");
            }
        }

        // (4) 결과 발표
        System.out.println("\n==================================");
        System.out.println("🎉 퀴즈 종료!");
        System.out.println("총 점수: " + score + "점 (만점: " + (totalQuestions * 2) + "점)");
        System.out.println("==================================");
    }

    // 4. 단어 등록 로직
    private void registerWord() {
        System.out.println("\n[ 새 단어 등록하기 ]");

        System.out.print("테마(주제) 입력 (예: 기초, 동물) : ");
        String theme = sc.next();

        System.out.print("한자 입력 (예: 愛) : ");
        String kanji = sc.next();

        System.out.print("요미가나 입력 (예: あい) : ");
        String yomigana = sc.next();

        System.out.print("한국어 뜻 입력 (예: 사랑) : ");
        String kread = sc.next();

        // 입력받은 걸 가방(Kanji 객체)에 담기
        // (번호, 날짜는 DB가 알아서 하니까 0, null로 채워도 됨)
        Kanji newKanji = new Kanji(0, kanji, yomigana, kread, null, theme);

        // 창고지기에게 전달 (INSERT 실행)
        int result = dao.insertKanji(newKanji);

        if (result > 0) {
            System.out.println("✅ 등록되었습니다!");
        } else {
            System.out.println("⚠️ 등록 실패! 에러를 확인하세요.");
        }
    }
}
