package com.kenny.section01.connection;

// java sql 패키지 안의 클래스를 사용한다.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 해당 DBMS와 계정에 맞는 Connection 객체를 생성할 수 있다. */
public class Application1 {

    public static void main(String[] args) {

        Connection con = null;
        try {
            // 문자열로 작성된 class 명칭이 잘못되었을 경우 ClassNotFoundException이 발생할 수 있으므로
            // Exception handling 한다.
            Class.forName("com.mysql.cj.jdbc.Driver");  // mysql Driver 클래스를 동적으로 로드하기 위함

            // DBMS 연결 정보가 잘못된 경우 connection 객체 생성이 불가능하므로 SQLExeception이 발생할 수 있다.
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "practice", "practice"); // con 객체가 실제 dbms와의 연결을 담당한다.
            // jdbc:mysql://ip:port번호
            System.out.println("con = " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

// java app -- dbms 와의 연결을 한다는 것 = 나와의 별도의 프로그램을 입출력 하는 것.
// stream은 os에서 자원을 빌려 쓰는 것이기 때문에 close가 필요하다.