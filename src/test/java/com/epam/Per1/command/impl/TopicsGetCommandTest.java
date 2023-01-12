package com.epam.Per1.command.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.MySql.MySqlTopicDao;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.service.impl.TopicService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TopicsGetCommandTest {

    @Test
    public void TopicsGetCommandTest_return_good() throws ServletException, IOException, DbException {
        TopicDao topicDao = Mockito.mock(MySqlTopicDao.class);
        Mockito.when(topicDao.countAllTopics()).thenReturn(3);

        TopicService topicService = new TopicService(topicDao);

        assertEquals(3, topicService.countAll());

//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        PagingParams pagingParams = new PagingParams(10, 3);
//        HttpSession session = req.getSession();
//
//        session.setAttribute("paging_topics", pagingParams);
//
//        System.out.println(new TopicsGetCommand().execute(req, resp));

    }


}