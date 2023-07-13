package me.gayoungkim.menu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gayoungkim.banner.domain.Banner;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  private String link;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
  private List<Menu> subMenus = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "parent_id", nullable = true)
  private Menu parent;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "banner_id")
  private Banner banner;

  @Builder
  public Menu(String title, String link){
    this.title = title;
    this.link = link;
  }

  public void update(String title, String link) {
    this.title = title;
    this.link = link;
  }

  public boolean isTopMenu() {
    return parent == null;
  }
}
