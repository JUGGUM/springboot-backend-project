package me.gayoungkim.menu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gayoungkim.menu.domain.Menu;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuRequestDto {

  @NotNull
  @Size(min = 1, max = 10)
  private String title;

  @NotNull
  private String link;

  public Menu toEntity() {
    return Menu.builder()
        .title(title)
        .link(link).build();
  }
}
