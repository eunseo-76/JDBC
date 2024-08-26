package com.kenny.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.kenny.common.JDBCTemplate.close;

public class MenuRepository {
    public int updateMenu(Connection con, Menu menu) {
        PreparedStatement ps = null;

        int result = 0;

        Properties props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/kenny/section01/insert/mapper/menu-mapper.xml"));
            String sql = props.getProperty("updateMenu");

            ps = con.prepareStatement(sql);

            ps.setString(1, menu.getMenuName());
            ps.setInt(2, menu.getMenuPrice());
            ps.setInt(3, menu.getMenuCode());

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
