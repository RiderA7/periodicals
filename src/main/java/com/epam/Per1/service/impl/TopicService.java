package com.epam.Per1.service.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.ITopicService;
import com.epam.Per1.utils.PagingParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TopicService implements ITopicService {

    private static Logger log = LogManager.getLogger(TopicService.class);
    private final TopicDao topicDao = DaoFactory.getInstance().getTopicDao();

    @Override
    public List<Topic> getAllTopics() {
        try {
            return topicDao.getAllTopics();
        } catch (DbException e) {
            log.error("Can't get list of all topics from DB!!!");
            return null;
        }
    }

    @Override
    public List<Topic> getLimitTopics(String where, String groupBy, String sort, PagingParams pagingParams) {
        int offset = pagingParams.getOffset();
        int limit = pagingParams.getLimit();
        try {
            return topicDao.getLimitTopics(where, groupBy, sort, offset, limit);
        } catch (DbException e) {
            log.error("Can't get list of topics from DB!!!");
            return null;
        }
    }

    @Override
    public Topic getTopicByName(String name) {
        return null;
    }

    @Override
    public Topic getTopicById(int id) {
        Optional<Topic> optionalTopic = Optional.empty();
        try {
            optionalTopic = topicDao.getTopicById(id);
        } catch (DbException e) {
            String error = "Can't get topic with id=" + id;
            log.error(error);
        }
        if (optionalTopic.isPresent()) return optionalTopic.get();
        else
            return null;
    }

    @Override
    public boolean updateTopic(Topic topic) {
        try {
            return topicDao.updateTopic(topic);
        } catch (DbException e) {
            log.error("Can't update topic id=" + topic.getId(), e);
        }
        return false;
    }

    @Override
    public boolean createTopic(Topic topic) {
        try {
            return topicDao.create(topic);
        } catch (DbException e) {
            log.error("Can't create topic id=" + topic.getId(), e);
        }
        return false;
    }

    @Override
    public int countAllTopics() {
        try {
            return topicDao.countAllTopics();
        } catch (DbException e) {
            log.error("Can't count topics in DB!!!");
            return 0;
        }
    }
}
