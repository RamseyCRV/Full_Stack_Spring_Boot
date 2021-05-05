package com.libra.Controllers;

import com.libra.Config.Constants.ConfigConstants;
import com.libra.Config.Constants.InitConstants;
import com.libra.Config.Constants.ProfileConstants;
import com.libra.Config.FileUploadUtil;
import com.libra.Models.User;
import com.libra.Service.CRUDService;
import com.libra.Service.UserService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * SignIn & SignUp Controller
 */
@Controller
public class InitController {

    @Autowired
    @Qualifier(InitConstants.CRUD_SERVICE_QUALIFIER)
    private CRUDService<User> crudService;
    @Autowired
    private UserService userService;

    /**
     * SignIn & SignUP Page
     */
    @GetMapping(InitConstants.URL_SIGN_IN)
    public String getSignInPage() {

        return InitConstants.HTML;
    }

    /**
     * Register user
     * @param multipartFile avatar photo
     */
    @PostMapping(value = InitConstants.URL_SIGN_UP)
    public String saveUser(@RequestParam(ProfileConstants.PARAM_AVATAR) MultipartFile multipartFile,
                           @RequestParam(ProfileConstants.PARAM_FIRST_NAME) String firstName,
                           @RequestParam(ProfileConstants.PARAM_LAST_NAME) String lastName,
                           @RequestParam(ProfileConstants.PARAM_USERNAME) String username,
                           @RequestParam(ProfileConstants.PARAM_PHONE) String phone,
                           @RequestParam(ProfileConstants.PARAM_EMAIL) String email,
                           @RequestParam(ProfileConstants.PARAM_PASSWORD) String password)
            throws IOException {
        User user = new User();

        if (multipartFile.getSize() > 0) {
            String avatar = username + ConfigConstants.PNG_EXTENSION;
            user.setAvatar(avatar);
            FileUploadUtil.saveFile(ConfigConstants.AVATAR_USER_PATH, avatar, multipartFile);
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);

        crudService.saveObject(user);

        return InitConstants.REDIRECT_TO_SIGN_IN;
    }

    /**
     * SingOut
     */
    @GetMapping(InitConstants.URL_SIGN_OUT)
    public String logout() {
        return InitConstants.URL_SIGN_UP;
    }

    @ResponseBody
    @RequestMapping(value = InitConstants.URL_CHECK_IF_USERNAME_IS_AVAILABLE, method = RequestMethod.POST, produces = "application/json")
    public String checkIfUsernameIsAvailable(@RequestParam("username") String username){
        if(BooleanUtils.isTrue(userService.checkIfUsernameExist(username))){
            return "{data: 'AVAILABLE'}";
        }else{
            return "{data: 'NOT'}";
        }
    }
}
