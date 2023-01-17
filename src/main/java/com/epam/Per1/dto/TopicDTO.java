package com.epam.Per1.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"name"})
public class TopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int pubs;

    public TopicDTO(){}
}
