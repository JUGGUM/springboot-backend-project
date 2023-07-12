package me.gayoungkim.banner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Banner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String imageUrl;

  @Builder
  public Banner(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
