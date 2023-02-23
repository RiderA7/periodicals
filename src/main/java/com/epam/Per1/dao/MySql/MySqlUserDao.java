package com.epam.Per1.dao.MySql;

import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.UserDao;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.utils.SqlParams;
import com.epam.Per1.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.Per1.utils.SqlUtils.*;

/**
 * DAO implementation for the MySQL DB
 *
 * @author Alexander Bukhalenkov
 */
public class MySqlUserDao implements UserDao {

    private static Logger log = LogManager.getLogger(MySqlUserDao.class);
    ConnectionPool connectionPool;

    public MySqlUserDao(String propertiesFile) {
        connectionPool = ConnectionPool.getInstance(propertiesFile);
    }


    private static User buildUser(ResultSet rs) throws SQLException {
//        StringBuilder out = new StringBuilder();
//        System.out.println(rs.toString());
        return new User.Builder()
                .setId(rs.getInt("user_id"))
                .setName(rs.getString("user_name"))
                .setLogin(rs.getString("user_login"))
                .setPassword(rs.getString("user_password_md5"))
                .setRole(new UserRole(rs.getInt("user_role"),rs.getString("role_name")))
//                .setRoleId(rs.getInt("user_role"))
                .setMoney(rs.getInt("user_money"))
                .setIsBlocked(rs.getInt("user_blocked")==1)
                .getUser();
    }

    @Override
    public Optional<User> login(String login, char[] password) throws DbException {
        String hashPassword = Utils.hash(password);
        try (Connection con = connectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(LOGIN)) {
            ps.setString(1, login);
            ps.setString(2, hashPassword);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(buildUser(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot login", e);
        }
    }

    @Override
    public boolean create(User user) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(CREATE_USER)){
            int k = 0;
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getName());
            ps.setString(++k, user.getPassword());
            ps.setLong(++k, user.getRoleId());
            if (ps.executeUpdate() == 0) {
                throw new DbException("User not created!");
            }
        } catch (SQLException e) {
            throw new DbException("User not created!",e);
        }
        return true;
    }

    @Override
    public boolean isLoginExist(String login) throws DbException {
        return getUserByLogin(login).isPresent();
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(buildUser(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find user with login '"+login+"'",e);
        }
    }

    @Override
    public Optional<User> getUserById(int id) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_USER_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(buildUser(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find user with login '"+id+"'",e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DbException {
        boolean updated = false;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_USER)){
            int k = 0;
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getName());
            ps.setString(++k, user.getPassword());
            ps.setLong(++k, user.getRole().getId());
            ps.setDouble(++k, user.getMoney());
            ps.setInt(++k, user.isBlocked()?1:0);
            ps.setLong(++k, user.getId());
//            System.out.println(ps);
            if (ps.executeUpdate() != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            throw new DbException("User not updated! (dao)",e);
        }
        return updated;
    }

    @Override
    public List<User> getAllUsers() throws DbException {
        SqlParams sqlParams = new SqlParams.Builder()
                .getSqlParams();
        return getLimitUsers(sqlParams);
    }

    @Override
    public int countAllUsers() throws DbException {
        int count = 0;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(COUNT_ALL_USERS)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count = rs.getInt(1);
                }
        } catch (SQLException e) {
            throw new DbException("Can't count users", e);
        }
        return count;
    }

    @Override
    public List<User> getLimitUsers(SqlParams sqlParams) throws DbException {
        List<User> users = new ArrayList<>();
        String sql = SELECT_LIMIT_USERS;
        sql = Utils.prepareSqlWithPaging(sqlParams, sql);
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println(ps);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    User user = buildUser(rs);
                    users.add(user);
                }
        } catch (SQLException e) {
            throw new DbException("Can't get limited list of users", e);
        }
        return users;
    }

    @Override
    public void changeRole(int userId, int roleId) throws DbException {

    }

    @Override
    public void deleteUser(int userId) throws DbException {

    }
}
