package me.gayoungkim.menu.controller;

import lombok.RequiredArgsConstructor;
import me.gayoungkim.menu.domain.Menu;
import me.gayoungkim.menu.dto.MenuRequestDto;
import me.gayoungkim.menu.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuController {

  private final MenuService menuService;

  @PostMapping("/api/menus")
  public ResponseEntity<Menu> addMenu(@RequestBody MenuRequestDto requestDto) {
    Menu menu = menuService.save(requestDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(menu);
  }
}
