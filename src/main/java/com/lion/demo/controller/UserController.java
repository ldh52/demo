package com.lion.demo.controller;


import com.lion.demo.entity.User;
import com.lion.demo.service.UserService;
import java.time.LocalDate;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
        if (userService.findByUid(uid) == null && pwd.equals(pwd2) && pwd.length() >= 4) {
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            User user = User.builder()
                .uid(uid).pwd(hashedPwd).uname(uname).email(email).regDate(LocalDate.now())
                .role("ROLE_USER")
                .build();
            userService.registerUser(user);
        }
        return "redirect:/user/register";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/list";
    }

}