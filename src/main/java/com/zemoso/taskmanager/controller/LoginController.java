package com.zemoso.taskmanager.controller;

import com.zemoso.taskmanager.entity.Roles;
import com.zemoso.taskmanager.entity.Users;
import com.zemoso.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login")
    public String showLogin(){
        return "forms/login";
    }
    @RequestMapping("/signup")
    public String showSignUp(Model theModel){
        Users user=new Users();
        theModel.addAttribute("user",user);

        return "forms/signup";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") Users user){
        user.setEnabled((short) 1);
        String encoded=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        Roles role=new Roles("ROLE_EMPLOYEE");
        user.addRole(role);
        userService.createUser(user);

        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String displayProfile(Model theModel, Principal principal){
        String username=principal.getName();
        System.out.println(username);
     Users user= userService.getUserByUsername(username);
     theModel.addAttribute("userprofile",user);
        return "profile";
    }
    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
