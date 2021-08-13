package com.libra;

import com.libra.Models.Notes;
import com.libra.Repository.NotesRepository;
import com.libra.Service.NotesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotesTest {

    @Autowired
    private NotesService notesService;

    @Autowired
    private NotesRepository notesRepository;

    @Test
    private List<Notes> getNotes(){
        Notes notes = new Notes();
        return notesRepository.findAll();
    }
}
