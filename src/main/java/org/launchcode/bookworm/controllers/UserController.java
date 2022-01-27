package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.DTO.UserDTO;
import org.launchcode.bookworm.data.model.Authority;
import org.launchcode.bookworm.data.model.User;
import org.launchcode.bookworm.data.repository.AuthorityRepository;
import org.launchcode.bookworm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    @GetMapping("")
    public String index(Principal principal, Model model){
        String username = principal.getName();
        return "user/index";
    }

    @GetMapping("registration")
    public String displayAddUsersForm(Model model) {
        model.addAttribute("user",new UserDTO());
        return "user/registration";
    }

    @GetMapping("login")
    public String displayLoginForm(){
        return "user/login";
    }


    @PostMapping("registration")
    public String processAddUserForm(@ModelAttribute @Valid UserDTO newUser,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "user/registration";
        }

        String encodedPassword  = passwordEncoder.encode(newUser.getPassword());
        User dbUser = new User();
        dbUser.setUserName(newUser.getUserName());
        dbUser.setEmail(newUser.getEmail());
        dbUser.setFirstName(newUser.getFirstName());
        dbUser.setLastName(newUser.getLastName());
        dbUser.setPassword(encodedPassword);
        User savedUser = (User)userRepository.save(dbUser);
        Authority newAuthority = new Authority();
        newAuthority.setUser(savedUser.getId());
        newAuthority.setAuthority("USER");
        authorityRepository.save(newAuthority);
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
