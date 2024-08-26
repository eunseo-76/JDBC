package com.kenny.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.kenny.common.JDBCTemplate.close;

// repository : db에 접근해서 수행해야하는 crud를 담당하는 클래스
/* DBMS를 통해 수행하는 CRUD 작업 단위 메소드 */
public class MenuRepository {

    public int insertMenu(Connection con, Menu menu) {
        PreparedStatement ps = null;

        int result = 0;

        Properties props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/kenny/section01/insert/mapper/menu-mapper.xml"));
            String sql = props.getProperty("insertMenu");

            ps = con.prepareStatement(sql);

            ps.setString(1, menu.getMenuName());
            ps.setInt(2, menu.getMenuPrice());
            ps.setInt(3, menu.getCategoryCode());
            ps.setString(4, menu.getOrderableStatus());

            result = ps.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }

        return result;
    }
}

// connection은 service에서 다루기 때문에 close 할 필요 없음
// preparedstatement는 repo에서 다루고 repo에서 끝나므로 닫아주어야 함