package me.gayoungkim.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gayoungkim.banner.domain.Banner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BannerRequestDto {

  private String imageUrl;

  public Banner toEntity() {
    return Banner.builder()
        .imageUrl(imageUrl)
        .build();
  }
}
