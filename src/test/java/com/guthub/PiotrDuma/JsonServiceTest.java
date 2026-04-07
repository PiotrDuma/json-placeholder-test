package com.guthub.PiotrDuma;

import com.github.PiotrDuma.service.JsonService;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonServiceTest {

  private JsonService service;

  @BeforeEach
  void setUp() {
    this.service = new JsonService();
  }

  @Test
  @DisplayName("GET should return valid status code")
  void getAllStatusCodeTest() {
    Response response = service.getRequest();

    Assertions.assertThat(response.getStatusCode())
        .as("Check if GET method status code match")
        .isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("GET should return valid response header")
  void getAllResponseHeaderTest() {
    String expected = "application/json; charset=utf-8";
    Response response = service.getRequest();

    Assertions.assertThat(response.getContentType())
        .as("Check if GET method contains header param")
        .isNotNull()
        .isEqualTo(expected);
  }

  @Test
  @DisplayName("GET should return valid response body size")
  void getAllResponseSizeTest() {
    int expected = 10;
    Response response = service.getRequest();
    List<Object> jsonResponse = response.jsonPath().getList("");

    Assertions.assertThat(jsonResponse)
        .as("Check if GET method contains valid number of items")
        .doesNotContainNull()
        .hasSize(expected);
  }
}
