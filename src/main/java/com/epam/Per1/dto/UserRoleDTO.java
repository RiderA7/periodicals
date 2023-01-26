package com.epam.Per1.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"role"})
@ToString
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String role;

    public UserRoleDTO(int id, String role){
        this.id = id;
        this.role = role;
    }
}
