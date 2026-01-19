//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.conn.DBConnection;


public class Main {
    public static void main(String[] args) {
        while (true) {
            B b = new B();
            b.b();

            A a = new A();
            int result = a.a();

            if (result == 1) {
                System.out.println("저장완료");
            } else System.out.println("저장실패");
        }

    }
}

class A {

    PreparedStatement pstmt = null;
    Connection conn =null;
    ResultSet rs = null;
    String sql = "insert into \"TABLE\"( NAME, SEX, AFFILIATION, PHONENUM) VALUES ( ? ,? ,? ,?)";
    Scanner sc = new Scanner(System.in);
    public int a() {
        int result = 0;
        try{
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);


            System.out.println("이름입력");
            String name = sc.next();
            System.out.println("성별입력");
            String sex = sc.next();
            System.out.println("소속");
            String aff = sc.next();
            System.out.println("전화번호입력");
            String phonenum = sc.next();

            pstmt.setString(1,name);
            pstmt.setString(2,sex);
            pstmt.setString(3,aff);
            pstmt.setString(4,phonenum);

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("예외발생");
        }
        return result;
        }

}
class B {
    PreparedStatement pstmt = null;
    Connection conn =null;
    ResultSet rs = null;
    List list;
    List<Javabean> nameList;

    public void b () {
        String sql = "SELECT * FROM \"TABLE\" ORDER BY NAME ASC";
        try{
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            String a = pstmt.toString();
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Javabean java = new Javabean();
                java.setName(rs.getString("NAME"));
                java.setNum(rs.getString("NUM"));
                java.setSex(rs.getString("SEX"));
                java.setAff(rs.getString("AFFILIATION"));
                java.setPhone(rs.getString("PHONENUM"));

                System.out.print(java.getNum());
                System.out.print(java.getName());
                System.out.print(java.getSex());
                System.out.print(java.getAff());
                System.out.println(java.getPhone());
            }


            System.out.println(a);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("예외발생");
        }


    }



}
