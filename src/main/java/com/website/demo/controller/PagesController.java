package com.website.demo.controller;

import com.website.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("pages")
public class PagesController {
    private final BookRepository bookRepository;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksPage() {
        System.out.println("Getting books page");
        return "index";
    }

    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public String bookPage(@PathVariable("id") final Integer id){
        System.out.println("Getting book page");
        return "book";
    }
}
