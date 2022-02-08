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
            return "redirect:add";
        }

        User user = userRepository.findUserData(principal.getName());

        newBook.setOwner(user);
        bookRepository.save(newBook);
        model.addAttribute("success", "The book has been added to your library!");
        model.addAttribute("book",new Book());
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

    @PostMapping("view/{bookId}")
    public String processBorrowBookForm(@ModelAttribute @Valid Book currentBook,
                                     Errors errors, Model model, Principal principal) {


        if (errors.hasErrors()) {
            return "redirect:view";
        }

        User borrower = userRepository.findUserData(principal.getName());

        currentBook.setBorrower(borrower);
        bookRepository.save(currentBook);
        model.addAttribute("success", "You've borrowed the book!");
        model.addAttribute("book",new Book());
        return "view/{bookId}";
    }


}
