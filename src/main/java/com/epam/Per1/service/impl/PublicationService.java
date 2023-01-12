package com.epam.Per1.service.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.PublicationDao;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.service.IService;
import com.epam.Per1.utils.SqlParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PublicationService implements IService<Publication> {

    private static Logger log = LogManager.getLogger(PublicationService.class);
    private PublicationDao publicationDao;

    public PublicationService(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public List<Publication> getAll(int topicId) {
        try {
            return publicationDao.getAll(topicId);
        } catch (DbException e) {
            log.error("Can't get list of all publications from DB!!!");
            return null;
        }
    }

    @Override
    public List<Publication> getAll() {
        try {
            return publicationDao.getAll(0);
        } catch (DbException e) {
            log.error("Can't get list of all publications from DB!!!");
            return null;
        }
    }

    @Override
    public List<Publication> getLimit(SqlParams sqlParams) {
        try {
            return publicationDao.getLimit(sqlParams);
        } catch (DbException e) {
            log.error("Can't get list of publications from DB!!!");
            return null;
        }
    }

    @Override
    public Optional<Publication> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Publication> getById(int id) {
        Optional<Publication> optionalPublication = Optional.empty();
        try {
            optionalPublication = publicationDao.getById(id);
        } catch (DbException e) {
            String error = "Can't get publication with id=" + id;
            log.error(error);
        }
        return optionalPublication;
    }

    @Override
    public boolean update(Publication publication) {
        try {
            return publicationDao.update(publication);
        } catch (DbException e) {
            log.error("Can't update publication id=" + publication.getId(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Publication publication) {
        return false;
    }

    @Override
    public boolean create(Publication publication) {
        try {
            return publicationDao.create(publication);
        } catch (DbException e) {
            log.error("Can't create publication id=" + publication.getId(), e);
        }
        return false;
    }

    @Override
    public int countAll() {
        try {
            return publicationDao.countAll(0);
        } catch (DbException e) {
            log.error("Can't count publications in DB!!!");
            return 0;
        }
    }

    public int countAll(int topicId) {
        try {
            return publicationDao.countAll(topicId);
        } catch (DbException e) {
            log.error("Can't count publications with topicId=" + topicId + " in DB!!!");
        }
        return 0;
    }
}
