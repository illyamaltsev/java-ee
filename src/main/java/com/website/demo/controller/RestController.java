package com.website.demo.controller;

import com.website.demo.dto.Book;
import com.website.demo.dto.BookResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("rest")
public class RestController {
    private static final List<Book> BOOK_DATABASE = new ArrayList<>();

    @ResponseBody
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> bookList(
            @RequestParam(name="s") Optional<String> search
    ) {
        System.out.println("Get books ");

        List<Book> response = BOOK_DATABASE;
        if (search.isPresent()) {
            String q = search.get();
            response = response
                    .stream()
                    .filter(book -> book.getTitle().contains(q) || book.getIsbn().contains(q))
                    .collect(Collectors.toList());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<BookResponseDto> addNewBook(
            @RequestBody final Book newBook
    ) {
        System.out.println("Add book");

        BOOK_DATABASE.add(newBook);
        return ResponseEntity.ok(BookResponseDto.of(newBook.getTitle(), "Created"));
    }
}
