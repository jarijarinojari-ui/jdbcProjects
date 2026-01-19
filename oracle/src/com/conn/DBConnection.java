package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection conn = null;
    public static Connection getConnection () {
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "STUDY";
        String pw = "1234";

        conn = DriverManager.getConnection(url,id,pw);
        System.out.println("연결성공");

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("DB 연결 실패");
        }
        return conn;
    }

}
