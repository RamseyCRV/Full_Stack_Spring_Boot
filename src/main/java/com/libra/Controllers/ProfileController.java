package com.libra.Controllers;

import com.libra.Config.Constants.ConfigConstants;
import com.libra.Config.Constants.InitConstants;
import com.libra.Config.Constants.ProfileConstants;
import com.libra.Config.FileUploadUtil;
import com.libra.Models.User;
import com.libra.Service.CRUDService;
import com.libra.Service.NotesService;
import com.libra.Service.TodoService;
import com.libra.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    @Qualifier(ProfileConstants.CRUD_SERVICE_QUALIFIER)
    public CRUDService <User> crudService;
    @Autowired
    public UserService userService;
    @Autowired
    public NotesService notesService;
    @Autowired
    public TodoService todoService;

    /**
     * Profile page
     * @return profile html
     */
    @GetMapping(ProfileConstants.URL_PAGE)
    public String getProfile(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final User activeUser = userService.returnCurrentSignInUser(username);
        int userNotes = notesService.countAllNotesForActiveUser(username);
        int userTodos = todoService.countAllTodosForActiveUser(username);

        model.addAttribute(ProfileConstants.MODEL_USER, activeUser);
        model.addAttribute(ProfileConstants.MODEL_COUNT_NOTES, userNotes);
        model.addAttribute(ProfileConstants.MODEL_COUNT_TODOS, userTodos);

        return ProfileConstants.HTML;
    }

    /**
     * Update user profile
     */
    @PostMapping(value = ProfileConstants.URL_EDIT_USER)
    public String editUser(@RequestParam("avatar") MultipartFile multipartFile,
                           @RequestParam("id") int id,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("phone") String phone,
                           @RequestParam("email") String email)
            throws IOException {

        User user = userService.returnCurrentSignInUser(SecurityContextHolder.getContext().getAuthentication().getName());

        if (multipartFile.getSize() > 0) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String avatar = username + ConfigConstants.PNG_EXTENSION;
            user.setAvatar(avatar);
            FileUploadUtil.saveFile(ConfigConstants.AVATAR_USER_PATH, avatar, multipartFile);
        }
        user.setPhone(phone);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.updateUserProfile(user);

        return ProfileConstants.URL_REDIRECT_TO_PAGE;
    }

    /**
     * Delete user account
     */
    @RequestMapping(value = ProfileConstants.URL_DELETE_USER, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUserAccount(int id, String password){
        crudService.deleteObject(id);

        return InitConstants.HTML;
    }

    /**
     * Find user by id
     */
    @RequestMapping(ProfileConstants.URL_FIND_USER_BY_ID)
    @ResponseBody
    public Optional<User> findUserById(int id){
        return crudService.findObjectById(id);
    }
}
