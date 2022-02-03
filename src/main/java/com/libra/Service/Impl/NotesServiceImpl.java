package com.libra.Service.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.NotesDao;
import com.libra.Models.NotesModel;
import com.libra.Service.CrudService;
import com.libra.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements CrudService<NotesModel>, NotesService {

    @Autowired
    private CrudDao<NotesModel> notesCrudDao;
    @Autowired
    private NotesDao notesDao;

    @Override
    public List<NotesModel> getObjects() {
        return notesCrudDao.getObjects();
    }

    @Override
    public Optional<NotesModel> findObjectById(int id) {
        return notesCrudDao.findObjectById(id);
    }

    @Override
    public void deleteObject(int id) {
        notesCrudDao.deleteObjectById(id);
    }

    @Override
    public void saveObject(NotesModel object) {
        notesCrudDao.saveObject(object);
    }

    @Override
    public List<NotesModel> findAllNotesForActiveUser(String username) {
        return notesDao.findNotesForActiveUser(username);
    }

    @Override
    public int countAllNotesForActiveUser(String username) {
        return notesDao.countAllNotesForActiveUser(username);
    }

    @Override
    public List<NotesModel> deleteAllNotesByActiveUser(String username) {
        return notesDao.deleteAllNotesByActiveUser(username);
    }

}
