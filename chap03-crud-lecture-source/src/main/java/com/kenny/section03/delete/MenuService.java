package com.kenny.section03.delete;

import java.sql.Connection;

import static com.kenny.common.JDBCTemplate.*;

public class MenuService {
    public void removeMenu(Menu menu) {

        Connection con = getConnection();

        MenuRepository menuRepository = new MenuRepository();
        int result = menuRepository.deleteMenu(con, menu);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);
    }
}
