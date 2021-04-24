package com.libra.Controllers;

import com.libra.Config.FileUploadUtil;
import com.libra.Models.User;
import com.libra.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
public class InitController {

    private final static String UPLOAD_DIR = "src/main/resources/static/images/avatars";

    @Autowired
    private UserServiceImpl userServiceImpl;

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
        userServiceImpl.saveUser(user);
        FileUploadUtil.saveFile(UPLOAD_DIR, avatar, multipartFile);

        return "redirect:/signIn";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, Authentication authentication) {
        String userName = authentication.getName();
        model.addAttribute("avatar", userName + ".png");
        return "home";
    }

    @GetMapping("/signOut")
    public String logout() {
        return "redirect:/signIn";
    }
}
