package com.epam.Per1.service.impl;

import com.epam.Per1.dao.MySql.MySqlSubscriptionDao;
import com.epam.Per1.dao.SubscriptionDao;
import com.epam.Per1.dto.*;
import com.epam.Per1.entity.Subscription;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.SqlParams;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubscriptionServiceTest {

    SubscriptionDao subscriptionDao = mock(MySqlSubscriptionDao.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        UserRoleDTO userRoleDTO = new UserRoleDTO(1, "user");
        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .login("user")
                .role(userRoleDTO)
                .money(BigDecimal.valueOf(1000))
                .build();
        TopicDTO topicDTO = TopicDTO.builder().id(1).name("testTopic").build();
        PublicationDTO publicationDTO = PublicationDTO.builder()
                .name("testPub")
                .price(BigDecimal.valueOf(100))
                .topic(topicDTO)
                .build();
        SubscriptionDTO subscriptionDTO = SubscriptionDTO.builder()
                .user(userDTO)
                .publication(publicationDTO)
                .status("ACTIVE")
                .build();
        Subscription subscription = Mapper.toSubscription(subscriptionDTO);
        when(subscriptionDao.create(any())).thenReturn(true);

        SubscriptionService subscriptionService = new SubscriptionService(subscriptionDao);

        assertTrue(subscriptionService.create(subscriptionDTO));
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void countAll() {
        SubscriptionService subscriptionService = new SubscriptionService(subscriptionDao);
        SqlParams sqlParams1 = new SqlParams.Builder()
                .setWhere("user = " + 1)
                .getSqlParams();
        SqlParams sqlParams2 = new SqlParams.Builder()
                .setWhere("user = " + 2)
                .getSqlParams();
        SqlParams sqlParams3 = new SqlParams.Builder()
                .setWhere("user = " + 3)
                .getSqlParams();

        when(subscriptionDao.countAll(sqlParams1)).thenReturn(5);
        assertEquals(5, subscriptionService.countAll(1));
        when(subscriptionDao.countAll(sqlParams2)).thenReturn(3);
        assertEquals(3, subscriptionService.countAll(2));
        when(subscriptionDao.countAll(sqlParams3)).thenThrow(new DbException("no such user"));
        assertEquals(0,subscriptionService.countAll(3));
    }
}