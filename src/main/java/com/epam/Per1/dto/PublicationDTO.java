package com.epam.Per1.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"name", "price"})
@ToString
public class PublicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private BigDecimal price;
    private TopicDTO topic;

}
