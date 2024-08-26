package com.kenny.section01.insert;

import java.sql.Connection;

import static com.kenny.common.JDBCTemplate.*;

/* Service 계층은 Connection 객체 생성 및 소멸(close), 비즈니스 로직, 트랜잭션(commit or rollback) 처리 */
public class MenuService {

    public void registMenu(Menu menu) {
        Connection con = getConnection();

        MenuRepository menuRepository = new MenuRepository();
        int result = menuRepository.insertMenu(con, menu);   // 커넥션, 입력받은 메뉴를 받아 메뉴 insert

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}

// service 클래스의 목적 :
// db로 insert를 해야 함.
// 특정 논리적 단위를 service 클래스의 메소드 단위로 분리할 예정.
// 이를 위한 connection 객체 필요.
// 비즈니스 로직이 완료가 되었다면 commit, 수행되지 않았다면 rollback 하고 사용한 객체를 close(return) 해야 함.
