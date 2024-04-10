package org.example.app.repository.impl;

import org.example.app.database.DBConn;
import org.example.app.entity.Users;
import org.example.app.repository.AppRepository;
import org.example.app.utils.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepository implements AppRepository<Users> {

    private final static String TABLE_USERS = "users";

    @Override
    public String create(Users users) {

        String sql = "INSERT INTO " + TABLE_USERS +
                " ( name , email) VALUES(?, ?)";

        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            pstmt.setString(1, users.getName());
            pstmt.setString(2, users.getEmail());
            pstmt.executeUpdate();

            return Constants.DATA_INSERT_MSG;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public Optional<List<Users>> read() {
        try (Statement stmt = DBConn.connect().createStatement()) {

            List<Users> list = new ArrayList<>();

            String sql = "SELECT id, name, email FROM "
                    + TABLE_USERS;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Users(
                                rs.getLong("id"),
                                rs.getString("Name"),
                                rs.getString("email")
                        )
                );
            }

            return Optional.of(list);
        } catch (SQLException e) {

            return Optional.empty();
        }
    }

    @Override
    public String update(Users users) {
        if (readById(users.getId()).isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {

            String sql = "UPDATE " + TABLE_USERS +
                    " SET name = ?, email = ?" +
                    " WHERE id = ?";

            try (PreparedStatement pst = DBConn.connect().prepareStatement(sql)) {

                pst.setString(1, users.getName());
                pst.setString(2, users.getEmail());
                pst.setLong(3, users.getId());

                pst.executeUpdate();

                return Constants.DATA_UPDATE_MSG;
            } catch (SQLException e) {

                return e.getMessage();
            }
        }
    }

    @Override
    public String delete(Long id) {

        if (!isIdExists(id)) {
            return Constants.DATA_ABSENT_MSG;
        } else {

            String sql = "DELETE FROM " + TABLE_USERS +
                    " WHERE id = ?";

            try (PreparedStatement pst = DBConn.connect().prepareStatement(sql)) {

                pst.setLong(1, id);

                pst.executeUpdate();

                return Constants.DATA_DELETE_MSG;
            } catch (SQLException e) {

                return e.getMessage();
            }
        }
    }

    @Override
    public Optional<Users> readById(Long id) {

        String sql = "SELECT id, name , email FROM "
                + TABLE_USERS + " WHERE id = ?";
        try (PreparedStatement pst = DBConn.connect().prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Users users = new Users(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

            return Optional.of(users);
        } catch (SQLException e) {

            return Optional.empty();
        }
    }


    private boolean isIdExists(Long id) {
        String sql = "SELECT COUNT(id) FROM " + TABLE_USERS +
                " WHERE id = ?";
        try {
            PreparedStatement pst = DBConn.connect().prepareStatement(sql);
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
