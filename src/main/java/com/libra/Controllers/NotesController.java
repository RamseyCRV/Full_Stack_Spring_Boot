package com.libra.Controllers;

import com.libra.Config.LibraConstants;
import com.libra.Config.LibraConstants.Controllers.Notes;
import com.libra.Service.CrudService;
import com.libra.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Notes page controller
 */
@Controller
@RequestMapping(value = Notes.URL_PAGE)
public class NotesController {

    @Autowired
    @Qualifier(Notes.CRUD_SERVICE_QUALIFIER)
    private CrudService<com.libra.Models.Notes> crudNotesService;
    @Autowired
    private NotesService notesService;

    /**
     * Get notes for active user
     */
    @GetMapping
    public ModelAndView getNotes(Model model){

        List<com.libra.Models.Notes> notes = notesService.findAllNotesForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute(Notes.MODEL_NOTES, notes);
        model.addAttribute("titlu", "sa vedem");

        return new ModelAndView(Notes.VIEW);
    }

    /**
     * Add note in DB
     */
    @PostMapping(Notes.URL_ADD)
    public String addNote(com.libra.Models.Notes note){
        crudNotesService.saveObject(note);

        return Notes.URL_REDIRECT;
    }

    /**
     * Delete note using id
     */
    @RequestMapping(value = Notes.URL_DELETE, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNote(int id){
        crudNotesService.deleteObject(id);

        return Notes.URL_REDIRECT;
    }

    /**
     * Find a note using id
     */
    @RequestMapping(Notes.URL_FIND_BY_ID)
    @ResponseBody
    public Optional<com.libra.Models.Notes> findNoteById(int id){
        return crudNotesService.findObjectById(id);
    }

    /**
     * Update a note
     */
    @RequestMapping(value = Notes.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateNote(com.libra.Models.Notes note){
        crudNotesService.saveObject(note);

        return Notes.URL_REDIRECT;
    }

}
