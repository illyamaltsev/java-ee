package com.website.demo.controller;

import com.website.demo.controller.dto.BookResponseDto;
import com.website.demo.domain.entities.BookEntity;
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

        return search.map(bookRepository::findByKeyword)
                .orElseGet(bookRepository::findAll);
    }

    @ResponseBody
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<BookResponseDto> addNewBook(
            @RequestBody final BookEntity newBook
    ) {
        System.out.println("Add book");

        BookEntity addedBook = bookRepository.saveAndFlush(newBook);
        return ResponseEntity.ok(BookResponseDto.of(addedBook.getId(), "Created"));
    }

    @ResponseBody
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Optional<BookEntity> bookList(@PathVariable("id") final Integer id) {
        System.out.println("Get book");

        return bookRepository.findById(id);
    }
}
