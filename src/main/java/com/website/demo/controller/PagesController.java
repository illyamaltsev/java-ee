package com.website.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("pages")
public class PagesController {
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksPage() {
        System.out.println("Getting books page");
        return "index";
    }
}
