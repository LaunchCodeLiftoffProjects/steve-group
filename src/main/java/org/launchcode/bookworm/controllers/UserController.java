package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.User;
import org.launchcode.bookworm.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUsersForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid User newUser,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "user/add";
        }

        userRepository.save(newUser);
        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable int userId) {

        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            return "user/view";
        } else {
            return "redirect:../";
        }
    }

}
