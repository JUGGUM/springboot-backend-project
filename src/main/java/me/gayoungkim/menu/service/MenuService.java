package me.gayoungkim.menu.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.gayoungkim.banner.domain.Banner;
import me.gayoungkim.banner.dto.BannerRequestDto;
import me.gayoungkim.banner.repository.BannerRepository;
import me.gayoungkim.menu.domain.Menu;
import me.gayoungkim.menu.dto.MenuRequestDto;
import me.gayoungkim.menu.dto.SubMenuRequestDto;
import me.gayoungkim.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {

  private final MenuRepository menuRepository;
  private final BannerRepository bannerRepository;

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

  public void delete(long id){
    menuRepository.deleteById(id);
  }

  public List<Menu> findAll() {
    return menuRepository.findAll();
  }

  public Menu findById(long id) {
    return menuRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Menu doesn't exist. id =" + id));
  }

  public Menu saveSubmenu(long id, SubMenuRequestDto subMenuRequestDto) {
    Menu menu = menuRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid menu"));

    if (!menu.isTopMenu()) {
      throw new IllegalArgumentException(("Banner can only be attached to top menu"));
    }

    Menu subMenu = menuRepository.save(subMenuRequestDto.toEntity());
    subMenu.setParent(menu);
    return menuRepository.save(subMenu);
  }

  public List<Menu> findSubMenus(long id) {
    Optional<Menu> menu = menuRepository.findById(id);
    return menu.get().getSubMenus();
  }

  public Menu updateBanner(long id, BannerRequestDto requestDto) {
    Menu menu = menuRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid menu"));

    if (!menu.isTopMenu()) {
      throw new IllegalArgumentException(("Banner can only be attached to top menu"));
    }

    Banner banner = bannerRepository.save(requestDto.toEntity());
    menu.setBanner(banner);
    return menuRepository.save(menu);
  }
}
