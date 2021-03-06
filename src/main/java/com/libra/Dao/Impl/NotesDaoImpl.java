package com.libra.Dao.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.NotesDao;
import com.libra.Models.NotesModel;
import com.libra.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotesDaoImpl implements CrudDao<NotesModel>, NotesDao {

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<NotesModel> getObjects() {
        return notesRepository.findAll();
    }

    @Override
    public Optional<NotesModel> findObjectById(int id) {
        return notesRepository.findById(id);
    }

    @Override
    public void deleteObjectById(int id) {
        notesRepository.deleteById(id);
    }

    @Override
    public void saveObject(NotesModel object) {
        notesRepository.save(object);
    }

    @Override
    public List<NotesModel> findNotesForActiveUser(String username) {
        return notesRepository.findByCreatedBy(username);
    }

    @Override
    public int countAllNotesForActiveUser(String username) {
        return notesRepository.countByCreatedBy(username);
    }

    @Override
    public List<NotesModel> deleteAllNotesByActiveUser(String username) {
        return notesRepository.deleteByCreatedBy(username);
    }
}
