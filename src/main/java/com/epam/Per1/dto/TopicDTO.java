package com.epam.Per1.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"name"})
@ToString
public class TopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int pubs;

}
