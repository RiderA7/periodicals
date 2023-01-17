package com.epam.Per1.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"user", "publication"})
public class SubscriptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserDTO user;
    private PublicationDTO publication;
    private String status;

    public SubscriptionDTO(){}
}
