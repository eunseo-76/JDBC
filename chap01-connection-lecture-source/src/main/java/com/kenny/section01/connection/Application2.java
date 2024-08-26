package com.kenny.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Properties 파일의 설정 정보를 읽어와 Connection 생성하기 */
public class Application2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(new FileReader("src/main/java/com/kenny/section01/connection/jdbc.config.properties"));    // properties 파일 내용을 읽어오는 load 메소드
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            Class.forName(driver);  // MySQL Driver 클래스 로딩
            con = DriverManager.getConnection(url, user, password); // 접속 주소, 계정 정보

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
        }
    }

    private static void close(Connection con) {
        try {
            if(con!=null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
