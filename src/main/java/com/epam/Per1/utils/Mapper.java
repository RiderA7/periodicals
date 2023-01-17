package com.epam.Per1.utils;

import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;

public class Mapper {

    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());

//    private static UserService userService = new UserService()

    public static User toUser(UserDTO userDTO){
        return new User.Builder()
                .getUser();
    }

    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .roleId(user.getRoleId())
                .role(user.getRoleId()==1?"USER":"ADMIN")
                .build();
    }
}
