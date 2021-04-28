package com.libra.Service;

import org.springframework.stereotype.Service;

@Service
public interface NotesService {

    int countAllNotesForActiveUser(final String username);

}
