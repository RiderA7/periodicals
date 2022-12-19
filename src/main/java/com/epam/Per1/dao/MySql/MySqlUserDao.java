package com.epam.Per1.dao.MySql;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.UserDao;
import com.epam.Per1.dao.entity.User;
import com.epam.Per1.utils.SqlUtils;
import com.epam.Per1.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlUserDao implements UserDao {

    private static User mapUser(ResultSet rs) throws SQLException {
        StringBuilder out = new StringBuilder();
//        System.out.println(rs.toString());
        return new User.Builder()
                .setId(rs.getLong("user_id"))
                .setName(rs.getString("user_name"))
                .setLogin(rs.getString("user_login"))
                .setRoleId(rs.getInt("user_role"))
                .setEmail(rs.getString("user_email"))
                .setCreateDate(rs.getTimestamp("user_create_date"))
                .getUser();
    }

    @Override
    public User login(String login, char[] password) throws DbException {
        String hashPassword = Utils.hash(password);
        try (Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(SqlUtils.LOGIN)) {
            ps.setString(1, login);
            ps.setString(2, hashPassword);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return null;
                return mapUser(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Cannot login", e);
        }
    }

    @Override
    public User signup(String login, char[] password) throws DbException {
        return null;
    }

    @Override
    public boolean isLoginExist(String login) throws DbException {
        return getUserByLogin(login) != null;
    }

    @Override
    public User getUserByLogin(String login) throws DbException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.FIND_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return null;
                return mapUser(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find user with login '"+login+"'",e);
        }
    }

    @Override
    public void changePassword(int userId, char[] newPassword) throws DbException {

    }

    @Override
    public List<User> getAllUsers() throws DbException {
        return null;
    }

    @Override
    public void changeRole(int userId, int roleId) throws DbException {

    }

    @Override
    public void deleteUser(int userId) throws DbException {

    }
}
