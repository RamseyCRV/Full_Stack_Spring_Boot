package com.libra.Controllers;

import com.libra.Config.LibraConstants.NotesConstants;
import com.libra.Models.Notes;
import com.libra.Service.Interface.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Notes page controller
 */
@Controller
public class NotesController {

    @Autowired
    @Qualifier(NotesConstants.CRUD_SERVICE_QUALIFIER)
    CrudService<Notes> crudNotesService;

    /**
     * Get notes for active user
     */
    @GetMapping(NotesConstants.URL_PAGE)
    public String getNotes(Model model){
        List<Notes> notes = crudNotesService.findObjectsForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute(NotesConstants.MODEL_NOTES, notes);

        return NotesConstants.HTML;
    }

    /**
     * Add note in DB
     */
    @PostMapping(NotesConstants.URL_SAVE)
    public String addNote(Notes note){
        crudNotesService.saveObject(note);

        return NotesConstants.REDIRECT_TO_NOTES;
    }

    /**
     * Delete note using id
     */
    @RequestMapping(value = NotesConstants.URL_DELETE, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNote(int id){
        crudNotesService.deleteObject(id);

        return NotesConstants.REDIRECT_TO_NOTES;
    }

    /**
     * Find a note using id
     */
    @RequestMapping(NotesConstants.URL_FIND_BY_ID)
    @ResponseBody
    public Optional<Notes> findNoteById(int id){
        return crudNotesService.findObjectById(id);
    }

    /**
     * Update a note
     */
    @RequestMapping(value = NotesConstants.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateNote(Notes note){
        crudNotesService.saveObject(note);

        return NotesConstants.REDIRECT_TO_NOTES;
    }

}
