package com.website.demo.controller;

import com.website.demo.dto.BookResponseDto;
import com.website.demo.repository.BookEntity;
import com.website.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("rest")
public class RestController {
    private final BookRepository bookRepository;

    @ResponseBody
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookEntity> bookList(
            @RequestParam(name="s") Optional<String> search
    ) {
        System.out.println("Get books ");

        return search.map(bookRepository::findBySearchKeyword)
                .orElseGet(bookRepository::allBooks);
    }

    @ResponseBody
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<BookResponseDto> addNewBook(
            @RequestBody final BookEntity newBook
    ) {
        System.out.println("Add book");

        BookEntity addedBook = bookRepository.addBook(newBook);
        return ResponseEntity.ok(BookResponseDto.of(addedBook.getId(), "Created"));
    }

    @ResponseBody
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public BookEntity bookList(@PathVariable("id") final Integer id) {
        System.out.println("Get book");

        return bookRepository.getBookById(id);
    }
}
