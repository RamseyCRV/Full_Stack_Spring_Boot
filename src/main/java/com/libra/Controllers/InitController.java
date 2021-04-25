package com.libra.Controllers;

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

@Controller
public class InitController {

    private final static String UPLOAD_DIR = "src/main/resources/static/images/avatars";

    @Autowired
    @Qualifier("userServiceImpl")
    private CRUDService<User> userService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "signIn";
    }

    @PostMapping(value = "/signUp")
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
            avatar = username + ".png";
        }

        User user = new User();
        user.setAvatar(username + ".png");
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        userService.saveObject(user);
        FileUploadUtil.saveFile(UPLOAD_DIR, avatar, multipartFile);

        return "redirect:/signIn";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/signOut")
    public String logout() {
        return "redirect:/signIn";
    }
}
