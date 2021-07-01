package com.libra.Service.Interface;

import com.libra.Models.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotesService {

    int countAllNotesForActiveUser(final String username);

    List<Notes> deleteAllNotesByActiveUser(final String username);

}
