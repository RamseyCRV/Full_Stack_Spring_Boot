package com.libra.Service.Impl;

import com.libra.Models.Notes;
import com.libra.Repository.NotesRepository;
import com.libra.Service.CRUDService;
import com.libra.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements CRUDService<Notes>, NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Notes> getObjects() {
        return notesRepository.findAll();
    }

    @Override
    public Optional<Notes> findObjectById(int id) {
        return notesRepository.findById(id);
    }

    @Override
    public void deleteObject(int id) {
        notesRepository.deleteById(id);
    }

    @Override
    public void saveObject(Notes object) {
        notesRepository.save(object);
    }

    @Override
    public List<Notes> findObjectsForActiveUser(String username) {
        return notesRepository.findByCreatedBy(username);
    }

    @Override
    public int countAllNotesForActiveUser(String username) {
        return notesRepository.countByCreatedBy(username);
    }

    @Override
    public List<Notes> deleteAllNotesByActiveUser(String username) {
        return notesRepository.deleteByCreatedBy(username);
    }
}
