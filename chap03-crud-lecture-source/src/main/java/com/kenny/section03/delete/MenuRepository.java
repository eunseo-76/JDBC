package com.kenny.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.kenny.common.JDBCTemplate.close;

public class MenuRepository {

    public int deleteMenu(Connection con, Menu menu) {
        PreparedStatement ps = null;

        int result = 0;

        Properties props = new Properties();

        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/kenny/section01/insert/mapper/menu-mapper.xml"));
            String sql = props.getProperty("deleteMenu");

            ps = con.prepareStatement(sql);

            ps.setInt(1, menu.getMenuCode());

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
