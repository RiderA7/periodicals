package com.epam.Per1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Builder
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String login;
    private String name;
    private String role;
}
