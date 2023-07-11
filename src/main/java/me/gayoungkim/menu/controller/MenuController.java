package me.gayoungkim.menu.controller;

import lombok.RequiredArgsConstructor;
import me.gayoungkim.menu.domain.Menu;
import me.gayoungkim.menu.dto.MenuRequestDto;
import me.gayoungkim.menu.dto.MenuResponseDto;
import me.gayoungkim.menu.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/api/menus/{id}")
  public ResponseEntity<Menu> updateMenu(@PathVariable long id,
      @RequestBody MenuRequestDto requestDto) {
    Menu menu = menuService.update(id, requestDto);
    return ResponseEntity.ok()
        .body(menu);
  }

  @DeleteMapping("/api/menus/{id}")
  public ResponseEntity<Void> deleteMenu(@PathVariable long id){
    menuService.delete(id);
    return ResponseEntity.ok()
        .build();
  }

  @GetMapping("/api/menus/{id}")
  public ResponseEntity<MenuResponseDto> findMenu(@PathVariable long id) {
    Menu menu = menuService.findById(id);

    return ResponseEntity.ok()
        .body(new MenuResponseDto(menu));
  }
}
