package com.epam.Per1.service.impl;

import com.epam.Per1.dao.SubscriptionDao;
import com.epam.Per1.dto.SubscriptionDTO;
import com.epam.Per1.entity.Subscription;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.exception.NoSuchElementException;
import com.epam.Per1.service.Service;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.SqlParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionService implements Service<SubscriptionDTO> {

    private static Logger log = LogManager.getLogger(SubscriptionService.class);
    private SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public List<SubscriptionDTO> getAll() {
        return null;
    }

    @Override
    public List<SubscriptionDTO> getLimit(SqlParams sqlParams) {
        List<Subscription> subscriptions;
        try {
            subscriptions = subscriptionDao.getLimit(sqlParams);
        } catch (DbException e) {
            log.error("Can't get list of subscriptions from DB!!!");
            return null;
        }
        log.debug("Subs list:"+subscriptions);
        return subscriptions.stream()
                .map(Mapper::toSubscriptionDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(SubscriptionDTO subscription) {
        return false;
    }

    @Override
    public SubscriptionDTO getByLogin(String name) throws NoSuchElementException {
        return null;
    }

    @Override
    public SubscriptionDTO getById(int id) throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean update(SubscriptionDTO subscription) {
        return false;
    }

    @Override
    public boolean delete(SubscriptionDTO subscription) {
        return false;
    }

    @Override
    public int countAll() {
        SqlParams sqlParams = new SqlParams.Builder().getSqlParams(); // blank SqlParams
        try {
            return subscriptionDao.countAll(sqlParams);
        } catch (DbException e) {
            log.error("Can't count publications in DB!!!");
            return 0;
        }
    }
    public int countAll(int userId) {
        SqlParams sqlParams = new SqlParams.Builder()
                .setWhere("user = " + userId)
                .getSqlParams();
        try {
            return subscriptionDao.countAll(sqlParams);
        } catch (DbException e) {
            log.error("Can't count publications in DB!!!");
            return 0;
        }
    }
}
