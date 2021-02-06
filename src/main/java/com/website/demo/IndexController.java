package com.website.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private static final List<Book> BOOK_DATABASE = new ArrayList<Book>();


    @RequestMapping(value = "/book-list", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", BOOK_DATABASE);
        return "index";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute Book book) {
        BOOK_DATABASE.add(book);
        return "redirect:/book-list";
    }
}
