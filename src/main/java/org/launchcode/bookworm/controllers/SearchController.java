package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search.html")
public class SearchController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "search";
    }

}
