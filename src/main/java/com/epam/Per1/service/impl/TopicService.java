package com.epam.Per1.service.impl;

import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.exception.NoSuchElementException;
import com.epam.Per1.service.Service;
import com.epam.Per1.utils.SqlParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TopicService implements Service<Topic> {

    private static Logger log = LogManager.getLogger(TopicService.class);
    private TopicDao topicDao;

    public TopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

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
    public List<Topic> getLimit(SqlParams sqlParams) {
        try {
            return topicDao.getLimitTopics(sqlParams);
        } catch (DbException e) {
            log.error("Can't get list of topics from DB!!!");
            return null;
        }
    }

    @Override
    public Topic getByLogin(String name) throws NoSuchElementException{
        Optional<Topic> topic;
        try {
            topic = topicDao.getTopicByName(name);
        } catch (DbException e) {
            String error = "Can't find Topic with name " + name;
            log.error(error, e);
            throw new NoSuchElementException(error);
        }
        return topic.get();
    }

    @Override
    public Topic getById(int id) throws NoSuchElementException{
        Optional<Topic> optionalTopic = Optional.empty();
        try {
            optionalTopic = topicDao.getTopicById(id);
        } catch (DbException e) {
            String error = "Can't get topic with id=" + id;
            log.error(error);
            throw new NoSuchElementException(error);
        }
        return optionalTopic.get();
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
