package me.gayoungkim.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.security.Principal;
import me.gayoungkim.base.error.ErrorCode;
import me.gayoungkim.menu.dto.MenuRequestDto;
import me.gayoungkim.menu.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  MenuRepository menuRepository;

  @BeforeEach
  public void mockMvcSetup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .build();
    menuRepository.deleteAll();
  }

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

  @DisplayName("findArticle: 잘못된 HTTP 메서드로 아티클을 조회하려고 하면 조회에 실패한 다.")
  @Test
  public void invalidHttpMethod() throws Exception {
    // given
    final String url = "/api/menus/{id}";
    // when
    final ResultActions resultActions = mockMvc.perform(post(url, 1));
    // then
    resultActions
        .andDo(print())
        .andExpect(status().isMethodNotAllowed())
        .andExpect(jsonPath("$.message").value(ErrorCode.METHOD_NOT_ALLOWED.
            getMessage()));
  }
}