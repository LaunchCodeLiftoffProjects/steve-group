package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.data.model.Book;
import org.launchcode.bookworm.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    BookRepository bookRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("author", "Author");
        columnChoices.put("title", "Title");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("books",bookRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "books")
    public String listJobsByColumnAndValue(Model model, @RequestParam String searchTerm) {
        Iterable<Book> books = null;
            model.addAttribute("title", "Books with " + ": " + searchTerm);
            model.addAttribute("books", books);

        return "list-books";
    }

}
