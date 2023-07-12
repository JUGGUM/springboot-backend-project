package me.gayoungkim.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.security.Principal;
import me.gayoungkim.menu.dto.MenuRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @DisplayName("addMenu : 메뉴를 추가할때 title이 null이면 실패한다.")
  @Test
  public void addMenuNullValidation() throws Exception {

    //given
    final String url = "/api/menus";
    final String title = null;
    final String link = "link";
    final MenuRequestDto menuRequestDto = new MenuRequestDto(title, link);

    final String requestBody = objectMapper.writeValueAsString(menuRequestDto);

    Principal principal = Mockito.mock(Principal.class);
    Mockito.when(principal.getName()).thenReturn("username");

    // when
    ResultActions result = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .principal(principal)
        .content(requestBody));

    // then
    result.andExpect(status().isBadRequest());
  }

  @DisplayName("addMenu: 메뉴 추가할 때 title이 10자를 넘으면 실패한다.")
  @Test
  public void addMenuSizeValidation() throws Exception {

    // given
    Faker faker = new Faker();
    final String url = "/api/menus";
    final String title = faker.lorem().characters(11);
    final String link = "link";
    final MenuRequestDto userRequest = new MenuRequestDto(title, link);
    final String requestBody = objectMapper.writeValueAsString(userRequest);

    Principal principal = Mockito.mock(Principal.class);
    Mockito.when(principal.getName()).thenReturn("username");

    // when
    ResultActions result = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .principal(principal)
        .content(requestBody));

    // then
    result.andExpect(status().isBadRequest());
  }
}