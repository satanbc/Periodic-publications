package org.app.service;

import org.app.dao.PublicationDao;
import org.app.model.Publication;

import java.util.List;

public class PublicationService {

    private final PublicationDao publicationDao = new PublicationDao();

    public List<Publication> findAll() {
        return publicationDao.findAll();
    }

    public Publication findById(int id) {
        return publicationDao.findById(id);
    }

    public void create(Publication publication) {
        publicationDao.create(publication);
    }

    public boolean update(Publication publication) {
        return publicationDao.update(publication);
    }

    public boolean delete(int id) {
        return publicationDao.delete(id);
    }
}
