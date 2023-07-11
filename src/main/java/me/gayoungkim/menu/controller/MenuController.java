package me.gayoungkim.menu.controller;

import lombok.RequiredArgsConstructor;
import me.gayoungkim.menu.service.MenuService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;
}
