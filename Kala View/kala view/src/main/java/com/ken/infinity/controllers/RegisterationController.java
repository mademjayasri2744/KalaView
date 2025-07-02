package com.ken.infinity.controllers;

import com.ken.infinity.models.User;
import com.ken.infinity.services.UserService;
import javax.naming.Binding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterationController {
    private final UserService userService;

    @Autowired
    public RegisterationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (userService.userExists(user.getEmail())) {
            model.addAttribute("emailError", true); // This works with your register.html
            return "register"; // Stay on same page with error
        }

        userService.save(user);
        System.out.println(user);
        return "redirect:/login"; // Proper redirect to login
    }
}
