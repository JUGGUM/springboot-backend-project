package me.gayoungkim.menu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.gayoungkim.menu.domain.Menu;
import me.gayoungkim.menu.dto.MenuRequestDto;
import me.gayoungkim.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {

  private final MenuRepository menuRepository;

  public Menu save(MenuRequestDto requestDto) {
    return menuRepository.save(requestDto.toEntity());
  }

  @Transactional
  public Menu update(long id, MenuRequestDto requestDto) {
    Menu menu = menuRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Menu doesn't exist. id =" + id));

    menu.update(requestDto.getTitle(), requestDto.getLink());

    return menu;
  }
}
