package com.kenny.section02.prepared;

import java.sql.*;

import static com.kenny.common.JDBCTemplate.close;
import static com.kenny.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        /* 트랜잭션 처리를 위한 DBMS 연동용 Connection 객체 생성 */
        // (1) 연결
        Connection con = getConnection();   // JDBCTemplate의 getConnection() 메소드 : 특정 파일에서 설정 정보를 읽어와 DB와의 연결 객체를 만들어 반환
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            // PreparedStatement : 쿼리를 운반하고 결과를 반환하는 객cp
            // PreparedStatement(sql) : 객체 생성 시 수행할 sql 구문이 미리 전달됨
            // (2) 구문을 날리기 위한 statement 객체 생성
            pstmt = con.prepareStatement("SELECT * FROM employee");
            // ResultSet : Statement 객체를 통해 조회 처리된 결과를 다루는 객체
            // (3) 쿼리의 결과를 다루기 위한 ResultSet
            // 객체 생성 시 이미 sql 구문이 전달되었으므로 실행 시에느 전달하지 않는다.
            rset = pstmt.executeQuery();

            // rset.next() 해당 행의 존재 여부 확인
            while (rset.next()) {   // 결과 행이 존재하는지 확인

                // rset 은 1행을 참조하고 있으므로 해당 행에서 필요한 컬럼을 getXXX 메소드로 타입을 맞추어 꺼내올 수 있다.
                System.out.print(rset.getString("emp_name") + " ");
                System.out.println(rset.getInt("salary"));
            }
            // rset은 employee의 모든 행을 가져옴. getXXX로 그 중 원하는 부분을 가져옴.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /* 생성과 달리 역순으로 각 스트림을 닫는다. */
            close(rset);
            close(pstmt);   // pstmt 는 stmt의 자식이므로 pstmt를 위한 별도의 close 메소드를 만들 필요는 없다
            close(con);
        }
    }
}

// connection 뿐 아니라 statement, resultset 역시 사용 후 닫아주어야 한다.