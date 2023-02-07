package com.epam.Per1.service.impl;

import com.epam.Per1.dao.PublicationDao;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.exception.NoSuchElementException;
import com.epam.Per1.service.Service;
import com.epam.Per1.utils.SqlParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PublicationService implements Service<Publication> {

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
    public Publication getByLogin(String name) {
        Optional<Publication> optionalPublication;
        try {
            optionalPublication = publicationDao.getByName(name);
        } catch (DbException e) {
            String error = "Can't find publication with title " + name;
            log.error(error, e);
            throw new NoSuchElementException(error);
        }
        return optionalPublication.get();
    }

    @Override
    public Publication getById(int id) {
        Optional<Publication> optionalPublication = Optional.empty();
        try {
            optionalPublication = publicationDao.getById(id);
        } catch (DbException e) {
            String error = "Can't get publication with id=" + id;
            log.error(error);
            throw new NoSuchElementException(error);
        }
        return optionalPublication.get();
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
        try {
            return publicationDao.delete(publication);
        } catch (DbException e) {
            log.error("Can't delete publication id=" + publication.getId(), e);
        }
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
    public int countAll(SqlParams sqlParams) {
        try {
            return publicationDao.countAll(sqlParams);
        } catch (DbException e) {
            log.error("Can't count publications with sqlParams=" + sqlParams + " in DB!!!");
        }
        return 0;
    }
}
