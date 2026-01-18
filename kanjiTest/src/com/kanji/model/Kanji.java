package com.kanji.model;

import java.io.Serializable;

public class Kanji {
    // 변수선언
    private int kanjiNo;
    private String kanji;
    private String yomigana;
    private String korea;
    private String createDate;

    // 기본 생성자
    Kanji () {

    }

    // 모든필드 생성자
    public Kanji (int kanjiNo, String kanji, String yomigana, String korea, String createDate) {
        this.kanjiNo = kanjiNo;
        this.kanji = kanji;
        this.yomigana = yomigana;
        this.korea = korea;
        this.createDate = createDate;

    }
    // 게터세터

    public int getKanjiNo() {
        return kanjiNo;
    }

    public void setKanjiNo(int kanjiNo) {
        this.kanjiNo = kanjiNo;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getYomigana() {
        return yomigana;
    }

    public void setYomigana(String yomigana) {
        this.yomigana = yomigana;
    }

    public String getKorea() {
        return korea;
    }

    public void setKorea(String korea) {
        this.korea = korea;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
