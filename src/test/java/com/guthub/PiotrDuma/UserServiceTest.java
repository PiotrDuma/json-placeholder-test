package com.guthub.PiotrDuma;

import com.github.PiotrDuma.model.User;
import com.github.PiotrDuma.service.UserService;
import java.util.List;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

  private UserService service;

  @BeforeEach
  void setUp() {
    this.service = new UserService();
  }

  @Test
  @DisplayName("GET should return valid status code")
  void getAllStatusCodeTest() {
    service.getUsers()
        .isStatusCodeEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("GET should return valid response header")
  void getAllResponseHeaderTest() {
    String expected = "application/json; charset=utf-8";

    service.getUsers()
        .isStatusCodeEqualTo(HttpStatus.SC_OK)
        .isContentTypeEqualTo(expected);
  }

  @Test
  @DisplayName("GET should return valid response body size")
  void getAllResponseSizeTest() {
    int expected = 10;

    List<User> responseList = service.getUsers()
        .isStatusCodeEqualTo(HttpStatus.SC_OK)
        .getResponseList(User.class);

    Assertions.assertThat(responseList)
        .as("Check if GET method contains valid number of items")
        .doesNotContainNull()
        .hasSize(expected);
  }
}
