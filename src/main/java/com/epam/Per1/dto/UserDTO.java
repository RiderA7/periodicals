package com.epam.Per1.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"login","name"})
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String name;
    private BigDecimal money;
    private int roleId;
    private String role;
    private boolean isBlocked;

}
