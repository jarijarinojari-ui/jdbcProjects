package com.kanji.dao;

import com.kanji.common.DBCconnection;
import com.kanji.model.Kanji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KanjiDAO {

    // DB 연결에 필요한 객체들 선언
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 1. 단어 등록 (INSERT)
    // 사용자가 입력한 테마, 한자, 요미가나, 뜻을 DB에 저장합니다.
    public int insertKanji(Kanji kanji) {
        int result = 0;
        // 번호(SEQ)와 날짜(SYSDATE)는 자동이라 입력 안 해도 됨
        String sql = "INSERT INTO KANJI.KANJI (THEME, KANJI, YOMIGANA, KREAD) VALUES (?, ?, ?, ?)";

        try {
            conn = DBCconnection.getConnection(); // DB 연결
            pstmt = conn.prepareStatement(sql);

            // 물음표(?) 채우기
            pstmt.setString(1, kanji.getTheme());
            pstmt.setString(2, kanji.getKanji());
            pstmt.setString(3, kanji.getYomigana());
            pstmt.setString(4, kanji.getKread());

            result = pstmt.executeUpdate(); // 실행 (성공하면 1 반환)

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(); // 자원 해제
        }
        return result;
    }

    // 2. 테마 목록 가져오기 (SELECT DISTINCT)
    // 퀴즈 시작 전, 어떤 테마가 있는지 중복 없이 가져옵니다.
    public List<String> getThemeList() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT THEME FROM KANJI.KANJI ORDER BY THEME";

        try {
            conn = DBCconnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("THEME")); // 리스트에 테마 이름 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // 3. 퀴즈 문제 가져오기 (RANDOM SELECT)
    // 선택한 테마의 문제들을 랜덤하게 섞어서 가져옵니다.
    public List<Kanji> getQuizList(String theme) {
        List<Kanji> list = new ArrayList<>();
        // 랜덤 정렬 마법 (DBMS_RANDOM.VALUE)
        String sql = "SELECT * FROM KANJI.KANJI WHERE THEME = ? ORDER BY DBMS_RANDOM.VALUE";

        try {
            conn = DBCconnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, theme); // 사용자가 고른 테마 설정

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // DB에서 꺼낸 데이터를 Kanji 객체(가방)에 담기
                // 주의: Kanji 클래스 생성자 순서와 맞아야 함!
                Kanji k = new Kanji(
                        rs.getInt("KANJI_NO"),
                        rs.getString("KANJI"),
                        rs.getString("YOMIGANA"),
                        rs.getString("KREAD"),
                        rs.getString("CREATE_DATE"),
                        rs.getString("THEME")
                );
                list.add(k); // 리스트에 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // 4. 자원 해제 (청소)
    // DB 연결을 끊어주는 메서드 (메모리 누수 방지)
    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
