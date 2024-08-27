package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findByEmail(String email) {
        try (Connection conn = DBUtils.getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(String.format("SELECT * FROM user WHERE email = '%s'", email));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setCreatedTs(rs.getTimestamp(5));
                user.setUpdatedTs(rs.getTimestamp(6));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Bad connection", e);
        }


        return null;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }
}
