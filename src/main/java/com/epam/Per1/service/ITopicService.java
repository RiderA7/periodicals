package com.epam.Per1.service;

import com.epam.Per1.entity.Topic;
import com.epam.Per1.utils.PagingParams;

import java.util.List;

public interface ITopicService {

    List<Topic> getAllTopics();
    List<Topic> getLimitTopics(String where, String groupBy, String sort, PagingParams pagingParams);
    Topic getTopicByName(String name);
    Topic getTopicById(int id);
    boolean updateTopic(Topic topic);
    boolean createTopic(Topic topic);
    int countAllTopics();

}
