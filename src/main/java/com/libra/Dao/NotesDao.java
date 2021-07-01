package com.libra.Dao;

import com.libra.Models.Notes;

import java.util.List;

public interface NotesDao {

    List<Notes> findNotesForActiveUser(final String username);

    int countAllNotesForActiveUser(final String username);

    List<Notes> deleteAllNotesByActiveUser(final String username);
}
