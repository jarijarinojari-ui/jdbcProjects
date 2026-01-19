package com.kanji.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCconnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "KANJI";
            String pw = "1234";

            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("연결성공");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB 연결 실패 혹은 DB가 이미 생성되었 습니다.");
        }
        return conn;
    }
}
