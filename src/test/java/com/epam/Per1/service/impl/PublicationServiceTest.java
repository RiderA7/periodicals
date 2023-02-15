package com.epam.Per1.service.impl;

import com.epam.Per1.dao.MySql.MySqlPublicationDao;
import com.epam.Per1.dao.PublicationDao;
import com.epam.Per1.exception.DbException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PublicationServiceTest {

    PublicationDao publicationDao = mock(MySqlPublicationDao.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
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
    void create() {
    }

    @Test
    void countAll() {
        PublicationService publicationService = new PublicationService(publicationDao);
        when(publicationDao.countAll(1)).thenReturn(6);
        assertEquals(6, publicationService.countAll(1));

        when(publicationDao.countAll(2)).thenThrow(new DbException("DB error"));
        assertEquals(0, publicationService.countAll(2));

    }

    @Test
    void testCountAll() {
    }
}