package com.libra.Controllers;

import com.libra.Config.FileUploadUtil;
import com.libra.Config.LibraConstants.Controllers.Profile;
import com.libra.Config.LibraConstants.ConfigConstants;
import com.libra.Config.LibraConstants.Controllers.Init;
import com.libra.Models.User;
import com.libra.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * User Profile Controller
 */
@Controller
@RequestMapping(value = Profile.URL_PAGE)
public class ProfileController {

    @Autowired
    @Qualifier(Profile.CRUD_SERVICE_QUALIFIER)
    private CrudService<User> crudService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotesService notesService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private DeleteAccountsService deleteAccountsService;

    /**
     * Profile page
     */
    @GetMapping
    public String getProfile(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final User activeUser = userService.returnCurrentSignInUser(username);
        int userNotes = notesService.countAllNotesForActiveUser(username);
        int userTodos = todoService.countAllTodosForActiveUser(username);

        model.addAttribute(Profile.MODEL_USER, activeUser);
        model.addAttribute(Profile.MODEL_COUNT_NOTES, userNotes);
        model.addAttribute(Profile.MODEL_COUNT_TODOS, userTodos);

        return Profile.VIEW;
    }

    /**
     * Update user profile
     */
    @PostMapping(value = Profile.URL_EDIT_USER)
    public String editUser(@RequestParam(Profile.PARAM_AVATAR) MultipartFile multipartFile,
                           @RequestParam(Profile.PARAM_FIRST_NAME) String firstName,
                           @RequestParam(Profile.PARAM_LAST_NAME) String lastName,
                           @RequestParam(Profile.PARAM_PHONE) String phone,
                           @RequestParam(Profile.PARAM_EMAIL) String email)
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

        return Profile.URL_REDIRECT_TO_PAGE;
    }

    /**
     * Validate Delete user account
     */
    @GetMapping(Profile.URL_DELETE_USER)
    public String validateDeleteUserAccount(@RequestParam(Profile.PARAM_PASSWORD) String password){
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(password != null && userService.passwordIsCorrect(username, password)){
            deleteAccountsService.addAccountForDelete(username);
            return Init.REDIRECT_TO_SIGN_OUT;
        }
        return Profile.URL_REDIRECT_TO_PAGE;
    }

    /**
     * Change password for user
     */
    @RequestMapping(value = Profile.URL_EDIT_PASSWORD, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updatePassword(@RequestParam(Profile.PARAM_OLD_PASSWORD) String oldPassword,
                                 @RequestParam(Profile.PARAM_NEW_PASSWORD) String newPassword){

        if(userService.changePassword(SecurityContextHolder.getContext().getAuthentication().getName(), oldPassword, newPassword)){
            return Init.REDIRECT_TO_SIGN_OUT;
        }else{
            return Profile.URL_REDIRECT_TO_PAGE;
        }
    }

}
