package com.libra.Controllers;

import com.libra.Config.Constants.InitConstants;
import com.libra.Config.FileUploadUtil;
import com.libra.Models.User;
import com.libra.Service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
    private CRUDService<User> userService;

    /**
     * SignIn & SignUP Page
     * @return html page
     */
    @GetMapping(InitConstants.URL_SIGN_IN)
    public String getSignInPage() {
        return InitConstants.SIGN_IN_HTML;
    }

    /**
     * Register user
     * @param multipartFile avatar photo
     * @return Redirect to SignIn page
     * @throws IOException
     */
    @PostMapping(value = InitConstants.URL_SIGN_UP)
    public String saveUser(@RequestParam("avatar") MultipartFile multipartFile,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("username") String username,
                           @RequestParam("phone") String phone,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password)
            throws IOException {
        String avatar = "";

        if (multipartFile != null) {
            avatar = username + InitConstants.PNG_EXTENSION;
        }

        User user = new User();
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);

        userService.saveObject(user);

        FileUploadUtil.saveFile(InitConstants.AVATAR_USER_PATH, avatar, multipartFile);

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
