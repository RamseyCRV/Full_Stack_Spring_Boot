package com.libra.Controllers;

import com.libra.Config.LibraConstants.Views;
import com.libra.Config.LibraConstants.Controllers.Notes;
import com.libra.Models.NotesModel;
import com.libra.Service.CrudService;
import com.libra.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Notes page controller
 */
@Controller
@RequestMapping(value = Notes.URL_PAGE)
public class NotesController {

    @Autowired
    @Qualifier(Notes.CRUD_SERVICE_QUALIFIER)
    private CrudService<NotesModel> crudNotesService;
    @Autowired
    private NotesService notesService;
    @Autowired
    private MessageSource messageSource;

    /**
     * Notes page
     */
    @GetMapping
    public ModelAndView getNotesPage(Model model, Locale locale) {

        List<NotesModel> notes = notesService.findAllNotesForActiveUser(SecurityContextHolder
                .getContext().getAuthentication().getName());

        model.addAttribute(Notes.MODEL_NOTES, notes);
        model.addAttribute(Views.MODEL_PAGE_TITLE, Views.TITLE_PREFIX
                + messageSource.getMessage(Views.MENU_NOTES, null, locale));

        return new ModelAndView(Notes.VIEW);
    }

    /**
     * Add note
     */
    @PostMapping(Notes.URL_ADD)
    public String addNote(NotesModel note) {
        crudNotesService.saveObject(note);

        return Notes.URL_REDIRECT;
    }

    /**
     * Delete note using id
     */
    @RequestMapping(value = Notes.URL_DELETE, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNote(int id) {
        crudNotesService.deleteObject(id);

        return Notes.URL_REDIRECT;
    }

    /**
     * Find a note using id
     */
    @RequestMapping(Notes.URL_FIND_BY_ID)
    @ResponseBody
    public Optional<NotesModel> findNoteById(int id) {
        return crudNotesService.findObjectById(id);
    }

    /**
     * Update a note
     */
    @RequestMapping(value = Notes.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateNote(NotesModel note) {
        crudNotesService.saveObject(note);

        return Notes.URL_REDIRECT;
    }

}
