package com.epam.Per1.utils;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mapper {

    private static Logger log = LogManager.getLogger(Mapper.class);
    private static final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());

    public static User toUser(UserDTO userDTO){
        User user = userService.getByLogin(userDTO.getLogin()).orElseThrow();
        return new User.Builder()
                .setId(user.getId())
                .setLogin(userDTO.getLogin())
                .setName(userDTO.getName())
                .setMoney(Double.valueOf(userDTO.getMoney().doubleValue()*100.0).intValue())
                .setIsBlocked(userDTO.isBlocked()?1:0)
                .setRoleId(userDTO.getRoleId())
                .getUser();
    }

    public static UserDTO toUserDTO(User user){
        UserRole userRole;
        try {
            userRole = DaoFactory.getInstance().getUserRoleDao().getUserRole(user.getRoleId());
        } catch (DbException e) {
            userRole = new UserRole.Builder().setId(1).setUserRole("USER").getUserRole();
        }
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .roleId(user.getRoleId())
                .role(userRole.getUserRole())
                .isBlocked(user.isBlocked())
                .build();
    }
}
