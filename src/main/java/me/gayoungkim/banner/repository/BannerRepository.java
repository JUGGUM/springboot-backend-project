package me.gayoungkim.banner.repository;

import me.gayoungkim.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
