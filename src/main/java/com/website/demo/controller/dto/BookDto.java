package com.website.demo.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookDto {
    private Integer id;

    @NotNull(message = "ISBN cannot be empty")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "ISBN should have special format and consist of 13 digits")
    private String isbn;
    private String title;
    private String author;
}
