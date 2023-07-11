package me.gayoungkim.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gayoungkim.menu.domain.Menu;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuRequestDto {

  private String title;
  private String link;

  public Menu toEntity() {
    return Menu.builder()
        .title(title)
        .link(link).build();
  }
}
