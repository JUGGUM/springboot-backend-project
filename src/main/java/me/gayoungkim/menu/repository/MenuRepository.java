package me.gayoungkim.menu.repository;

import me.gayoungkim.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
