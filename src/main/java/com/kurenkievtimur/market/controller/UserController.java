package com.kurenkievtimur.market.controller;

import com.kurenkievtimur.market.DTO.CreateUserDTO;
import com.kurenkievtimur.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/logout";
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(CreateUserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }
}
