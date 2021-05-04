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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * SignIn & SignUP Page
     * @return html page
     */
    @GetMapping(InitConstants.URL_SIGN_IN)
    public String getSignInPage() {

        return InitConstants.HTML;
    }

    /**
     * Register user
     * @param multipartFile avatar photo
     * @return Redirect to SignIn page
     * @throws IOException
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
     * @return redirect to SignIn page
     */
    @GetMapping(InitConstants.URL_SIGN_OUT)
    public String logout() {
        return InitConstants.URL_SIGN_UP;
    }
}
