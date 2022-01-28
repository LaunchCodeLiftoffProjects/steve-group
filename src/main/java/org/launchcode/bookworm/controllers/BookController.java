package org.launchcode.bookworm.controllers;


import org.launchcode.bookworm.data.model.Book;
import org.launchcode.bookworm.data.model.User;
import org.launchcode.bookworm.data.repository.BookRepository;
import org.launchcode.bookworm.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/index";
    }

    @GetMapping("add")
    public String displayAddBooksForm(Model model) {
        model.addAttribute(new Book());
        return "books/add";
    }

    @PostMapping("add")
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors, Model model, Principal principal) {


        if (errors.hasErrors()) {
            return "books/add";
        }

        User user = userRepository.findUserData(principal.getName());

        newBook.setOwner(user);
        bookRepository.save(newBook);
        return "books/add";
    }

    @GetMapping("view/{bookId}")
    public String displayViewBook(Model model, @PathVariable int bookId) {

        Optional optBook = bookRepository.findById(bookId);
        if (optBook.isPresent()) {
            Book book = (Book) optBook.get();
            model.addAttribute("book", book);
            return "books/view";
        } else {
            return "redirect:../";
        }
    }

}
