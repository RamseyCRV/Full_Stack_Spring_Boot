package com.libra.Service;

import com.libra.Models.Notes;

import java.util.List;

public interface NotesService {

    List<Notes> findAllNotesForActiveUser(final String username);

    int countAllNotesForActiveUser(final String username);

    List<Notes> deleteAllNotesByActiveUser(final String username);

}
