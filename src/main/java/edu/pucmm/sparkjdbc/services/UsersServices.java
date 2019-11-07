package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersServices extends DatabaseManagement<User> {
    private static UsersServices instance;

    private UsersServices() {
        super(User.class);
    }

    public static UsersServices getInstance() {
        if (instance == null) {
            instance = new UsersServices();
        }
        return instance;
    }

    public User validateCredentials(String username, String password) {
        User user = null;

        Connection con = null;

        try {
            String query = "select *from users where username = ? and password = ?";
            con = DataBaseServices.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                user = new User();
                user.setUid(rs.getString("uid"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }


        } catch (SQLException ex) {

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {

            }
        }
        return user;
    }
}
