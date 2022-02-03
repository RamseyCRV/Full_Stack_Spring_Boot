package com.libra.Service;

import com.libra.Models.NotesModel;

import java.util.List;

public interface NotesService {

    List<NotesModel> findAllNotesForActiveUser(final String username);

    int countAllNotesForActiveUser(final String username);

    List<NotesModel> deleteAllNotesByActiveUser(final String username);

}
