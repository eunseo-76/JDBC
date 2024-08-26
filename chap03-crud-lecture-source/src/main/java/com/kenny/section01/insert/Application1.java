package com.kenny.section01.insert;

import java.sql.Connection;

import static com.kenny.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        System.out.println("con = " + con);
    }
}
