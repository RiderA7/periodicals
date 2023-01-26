package com.epam.Per1.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"login","name"})
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    @ToString.Exclude private transient String password;
    private String name;
    private BigDecimal money;
    private UserRoleDTO role;
    private boolean isBlocked;

}
