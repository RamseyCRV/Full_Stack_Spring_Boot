package com.libra.Controllers;

import com.libra.Config.FileUploadUtil;
import com.libra.Config.LibraConstants.Controllers.Init;
import com.libra.Config.LibraConstants.Controllers.Profile;
import com.libra.Config.LibraConstants.ConfigConstants;
import com.libra.Models.UserModel;
import com.libra.Service.CrudService;
import com.libra.Service.UserService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * SignIn & SignUp Controller
 */
@Controller
public class InitController {

    @Autowired
    @Qualifier(Init.CRUD_SERVICE_QUALIFIER)
    private CrudService<UserModel> crudService;
    @Autowired
    private UserService userService;

    /**
     * SignIn & SignUP Page
     */
    @GetMapping(value = Init.URL_SIGN_IN)
    public String getSignInPage() {

        return Init.VIEW;
    }

    /**
     * Register user
     * @param multipartFile avatar photo
     */
    @PostMapping(value = Init.URL_SIGN_UP)
    public String saveUser(@RequestParam(Profile.PARAM_AVATAR) MultipartFile multipartFile,
                           @RequestParam(Profile.PARAM_FIRST_NAME) String firstName,
                           @RequestParam(Profile.PARAM_LAST_NAME) String lastName,
                           @RequestParam(Profile.PARAM_USERNAME) String username,
                           @RequestParam(Profile.PARAM_PHONE) String phone,
                           @RequestParam(Profile.PARAM_EMAIL) String email,
                           @RequestParam(Profile.PARAM_PASSWORD) String password,
                           Model model)
            throws IOException {
        UserModel userModel = new UserModel();

        if (BooleanUtils.isTrue(userService.checkIfUsernameExist(username))) {
            return Init.REDIRECT_TO_SIGN_UP_ERROR;

        } else {
            if (multipartFile.getSize() > 0) {
                String avatar = username + ConfigConstants.PNG_EXTENSION;
                userModel.setAvatar(avatar);
                FileUploadUtil.saveFile(ConfigConstants.AVATAR_USER_PATH, avatar, multipartFile);
            }
            userModel.setEmail(email);
            userModel.setPassword(password);
            userModel.setUsername(username);
            userModel.setFirstName(firstName);
            userModel.setLastName(lastName);
            userModel.setPhone(phone);

            crudService.saveObject(userModel);

            return Init.REDIRECT_TO_SIGN_IN;
        }
    }

    /**
     * SingOut
     */
    @GetMapping(Init.URL_SIGN_OUT)
    public String logout() {
        return Init.URL_SIGN_UP;
    }

    /**
     * Username is already taken
     */
    @GetMapping(Init.URL_ERROR_SIGN_UP)
    public String errorSignUp(){
        return Init.VIEW_ERROR;
    }

}
