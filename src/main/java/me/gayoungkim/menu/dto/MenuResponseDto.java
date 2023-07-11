package me.gayoungkim.menu.dto;

import lombok.Getter;
import me.gayoungkim.menu.domain.Menu;

@Getter
public class MenuResponseDto {
  private final Long id;
  private final String title;
  private final String link;

  public MenuResponseDto(Menu menu) {
    this.id = menu.getId();
    this.title = menu.getTitle();
    this.link = menu.getLink();
  }
}
