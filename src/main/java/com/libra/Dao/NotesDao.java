package com.libra.Dao;

import com.libra.Models.NotesModel;

import java.util.List;

public interface NotesDao {

    List<NotesModel> findNotesForActiveUser(final String username);

    int countAllNotesForActiveUser(final String username);

    List<NotesModel> deleteAllNotesByActiveUser(final String username);
}
