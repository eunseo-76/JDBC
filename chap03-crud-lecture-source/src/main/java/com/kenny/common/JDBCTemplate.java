package com.kenny.common;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;


// connection이 필요하면 getConnection() 호출
// connection을 닫을 때는 close() 호출
// 위 두 가지 기능을 하는 template을 만들었다.


// 필요로 하는 커넥션이 발생하는 상황 마다 getConnection 호출할 수 있도록 템플릿화 함
public class JDBCTemplate {
    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(new FileReader("src/main/java/com/kenny/config/jdbc-config.properties"));    // properties 파일 내용을 읽어오는 load 메소드
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            Class.forName(driver);  // MySQL Driver 클래스 로딩
            con = DriverManager.getConnection(url, user, password); // 접속 주소, 계정 정보

            // url과 properties를 전달할 수도 있다.
//            DriverManager.getConnection(url, props); -> props 파일에서 user, password 키 값을 읽어서 처리

            // 자동 커밋 설정을 수동 커밋 설정으로 변경하여 서비스에서 처리할 수 있도록 한다. -> dml 구문의 commit, rollback 위함
            con.setAutoCommit(false);

            System.out.println("con = " + con);
        } catch (Exception e) {
            // 여러 Exception을 하나로 처리함
            throw new RuntimeException(e);
        }

        /* connection을 받는 개념은 별도로 분리하고 실제 닫는 시점은 추후 Service 계층에서 진행할 예정이다. */
        return con;
    }

    public static void close(Connection con) {
        try {
            if(con!=null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if (con != null && !con.isClosed()) {   // 해당 객체가 존재하고, 열려있을 경우 commit 한다.
                con.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null && !con.isClosed()) {   // 해당 객체가 존재하고, 열려있을 경우 rollback 한다.
                con.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
