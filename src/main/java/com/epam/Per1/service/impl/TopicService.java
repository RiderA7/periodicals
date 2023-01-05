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
        return null;
    }

    @Override
    public boolean updateTopic(Topic topic) {
        return false;
    }

    @Override
    public boolean createTopic(Topic topic) {
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
