package com.kenny.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.kenny.common.JDBCTemplate.close;
import static com.kenny.common.JDBCTemplate.getConnection;

/* 사번 입력 받아 사원 정보 조회하는 기능 */
public class Application2 {

    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.print("조회하고자 하는 사번 입력 : ");
            int empId = sc.nextInt();

            String query = "select emp_id, emp_name from employee where emp_id = '" + empId + "'";
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            } else {
                System.out.println("해당 사원의 조회 결과가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
