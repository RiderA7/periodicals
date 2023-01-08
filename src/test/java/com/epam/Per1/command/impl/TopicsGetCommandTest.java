package com.epam.Per1.command.impl;

import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.utils.PagingParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class TopicsGetCommandTest {

    @Test
    public void TopicsGetCommandTest_return_good() throws ServletException, IOException {
        TopicService topicService = Mockito.mock(TopicService.class);
        Mockito.when(topicService.countAll()).thenReturn(3);

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        PagingParams pagingParams = new PagingParams(10, 3);
        HttpSession session = req.getSession();

        session.setAttribute("paging_topics", pagingParams);

        System.out.println(new TopicsGetCommand().execute(req, resp));

    }


}