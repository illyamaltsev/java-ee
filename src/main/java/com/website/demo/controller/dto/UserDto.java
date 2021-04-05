package com.website.demo.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class UserDto {

    @NotEmpty(message = "Login can't be empty")
    @Pattern(regexp = "^[a-z]{5,10}$", message = "Login has bad format")
    private String login;

    @Size(min=8, max=20, message = "Password should have more than 8 and less than 20 characters")
    private String password;
}
