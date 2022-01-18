package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.Book;
import org.launchcode.bookworm.BookData;
import org.launchcode.bookworm.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.bookworm.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Book> books;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            books = bookRepository.findAll();
        } else {
            books = BookData.findByColumnAndValue(searchType, searchTerm, bookRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Books with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("books", books);

        return "search";
    }

}
