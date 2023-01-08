package com.epam.Per1.service.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.IService;
import com.epam.Per1.utils.PagingParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TopicService implements IService<Topic> {

    private static Logger log = LogManager.getLogger(TopicService.class);
    private final TopicDao topicDao = DaoFactory.getInstance().getTopicDao();

    @Override
    public List<Topic> getAll() {
        try {
            return topicDao.getAllTopics();
        } catch (DbException e) {
            log.error("Can't get list of all topics from DB!!!");
            return null;
        }
    }

    @Override
    public List<Topic> getLimit(String where, String groupBy, String sort, PagingParams pagingParams) {
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
    public Optional<Topic> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Topic> getById(int id) {
        Optional<Topic> optionalTopic = Optional.empty();
        try {
            optionalTopic = topicDao.getTopicById(id);
        } catch (DbException e) {
            String error = "Can't get topic with id=" + id;
            log.error(error);
        }
        return optionalTopic;
    }

    @Override
    public boolean update(Topic topic) {
        try {
            return topicDao.updateTopic(topic);
        } catch (DbException e) {
            log.error("Can't update topic id=" + topic.getId(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Topic topic) {
        return false;
    }

    @Override
    public boolean create(Topic topic) {
        try {
            return topicDao.create(topic);
        } catch (DbException e) {
            log.error("Can't create topic id=" + topic.getId(), e);
        }
        return false;
    }

    @Override
    public int countAll() {
        try {
            return topicDao.countAllTopics();
        } catch (DbException e) {
            log.error("Can't count topics in DB!!!");
            return 0;
        }
    }
}
