package com.epam.Per1.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"user", "publication"})
@ToString
public class SubscriptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private UserDTO user;
    private PublicationDTO publication;
    private String status;

}
