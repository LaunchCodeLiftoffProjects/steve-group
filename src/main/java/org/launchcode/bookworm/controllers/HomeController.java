package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.data.BookRepository;
import org.launchcode.bookworm.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Users");
        model.addAttribute("users",userRepository.findAll());

        return "index";
    }

    @GetMapping("index.html")
    public String rendorFormMethodName(Model model) {
        //Method code ...

        return "index";
    }


}
