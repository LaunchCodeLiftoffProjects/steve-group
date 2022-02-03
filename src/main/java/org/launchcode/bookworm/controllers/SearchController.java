package org.launchcode.bookworm.controllers;

import org.launchcode.bookworm.data.model.Book;
import org.launchcode.bookworm.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping("")
//    public String searchByTerm(Model model) {
//        model.addAttribute("books", bookRepository.findAll());
//        return "search";
//    }

//    @GetMapping("/results")
//    public String displayViewBook(Model model, @PathVariable int bookId) {
//
//        Optional optBook = bookRepository.findById(bookId);
//        if (optBook.isPresent()) {
//            Book book = (Book) optBook.get();
//            model.addAttribute("book", book);
//            return "books/view";
//        } else {
//            return "redirect:../";
//        }
//    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm){
        Iterable<Book> books = null;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            books = bookRepository.findAll();
        }
        model.addAttribute("title", "Books with " + searchTerm + ": " + searchTerm);
        model.addAttribute("books", books);

        return "results";
    }

}
