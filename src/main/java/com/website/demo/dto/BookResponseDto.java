package com.website.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookResponseDto {
    private final Integer id;
    private final String message;
}