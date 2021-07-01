package com.libra.Controllers;

import com.libra.Config.FileUploadUtil;
import com.libra.Config.LibraConstants.ProfileConstants;
import com.libra.Config.LibraConstants.ConfigConstants;
import com.libra.Config.LibraConstants.InitConstants;
import com.libra.Models.User;
import com.libra.Service.Interface.*;
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
public class ProfileController {

    @Autowired
    @Qualifier(ProfileConstants.CRUD_SERVICE_QUALIFIER)
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
    public String editUser(@RequestParam(ProfileConstants.PARAM_AVATAR) MultipartFile multipartFile,
                           @RequestParam(ProfileConstants.PARAM_FIRST_NAME) String firstName,
                           @RequestParam(ProfileConstants.PARAM_LAST_NAME) String lastName,
                           @RequestParam(ProfileConstants.PARAM_PHONE) String phone,
                           @RequestParam(ProfileConstants.PARAM_EMAIL) String email)
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
     * Validate Delete user account
     */
    @GetMapping(ProfileConstants.URL_DELETE_USER)
    public String validateDeleteUserAccount(@RequestParam(ProfileConstants.PARAM_PASSWORD) String password){
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(password != null && userService.passwordIsCorrect(username, password)){
            deleteAccountsService.addAccountForDelete(username);
            return InitConstants.REDIRECT_TO_SIGN_OUT;
        }
        return ProfileConstants.URL_REDIRECT_TO_PAGE;
    }

    /**
     * Change password for user
     */
    @RequestMapping(value = ProfileConstants.URL_EDIT_PASSWORD, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updatePassword(@RequestParam(ProfileConstants.PARAM_OLD_PASSWORD) String oldPassword,
                                 @RequestParam(ProfileConstants.PARAM_NEW_PASSWORD) String newPassword){

        if(userService.changePassword(SecurityContextHolder.getContext().getAuthentication().getName(), oldPassword, newPassword)){
            return InitConstants.REDIRECT_TO_SIGN_OUT;
        }else{
            return ProfileConstants.URL_REDIRECT_TO_PAGE;
        }
    }

}
