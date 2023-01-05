package com.epam.Per1.dao.MySql;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.UserRoleDao;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.utils.SqlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRoleDao implements UserRoleDao {

    private static Logger log = LogManager.getLogger(MySqlUserRoleDao.class);
    ConnectionPool connectionPool;

    public MySqlUserRoleDao(String propertiesFile) {
        connectionPool = ConnectionPool.getInstance(propertiesFile);
    }

    private static UserRole buildUserRole(ResultSet rs) throws SQLException {
        return new UserRole.Builder()
                .setId(rs.getLong("role_id"))
                .setUserRole(rs.getString("role_name"))
                .getUserRole();
    }

    @Override
    public List<UserRole> getAllUserRoles() throws DbException {
        List<UserRole> userRoles = new ArrayList<>();
        try (Connection con = connectionPool.getConnection();
             Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(SqlUtils.GET_ALL_USER_ROLES);
            while (rs.next()) {
                userRoles.add(buildUserRole(rs));
            }
            return userRoles;
        } catch (SQLException e) {
            throw new DbException("Can't get all userRoles", e);
        }
    }

    @Override
    public UserRole getUserRole(Long id) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_USER_ROLE_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return null;
                return buildUserRole(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find role with id '"+id+"'",e);
        }
    }
}
