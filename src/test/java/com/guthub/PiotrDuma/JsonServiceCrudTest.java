package com.guthub.PiotrDuma;

import com.github.PiotrDuma.model.UserDto;
import com.github.PiotrDuma.model.UserDto.AddressDTO;
import com.github.PiotrDuma.model.UserDto.CompanyDTO;
import com.github.PiotrDuma.model.UserDto.GeoDTO;
import com.github.PiotrDuma.service.JsonService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonServiceCrudTest {

  private JsonService service;

  @BeforeEach
  void setUp() {
    this.service = new JsonService();
  }

  @Test
  @DisplayName("GET by id should return an object")
  void getMethodShouldReturnByIdTest() {
    int id = 1;

    Response response = service.getRequest(id);

    Assertions.assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.SC_OK);
    Assertions.assertThat(response.getBody().as(UserDto.class))
        .as("Check if received object has valid id")
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", id);
  }

  @Test
  @DisplayName("GET by invalid id should return 404")
  void getMethodShouldReturnNotFoundTest() {
    int id = 12;

    Response response = service.getRequest(id);

    Assertions.assertThat(response.getStatusCode())
        .as("Check if response status code is 404")
        .isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("POST request should create valid object")
  void postMethodShouldReturnByIdTest() {
    UserDto dto = getDto();

    Response response = service.postRequest(dto);
    UserDto result = response.getBody().as(UserDto.class);

    Assertions.assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.SC_CREATED);
    Assertions.assertThat(result)
        .as("Check if created object is valid")
        .isNotNull()
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(dto);
  }

  @Test
  @DisplayName("PUT request should return updated object")
  void putMethodShouldReturnByIdTest() {
    int id = 2;
    UserDto dto = getDto();
    dto.setId(2);

    Response response = service.putRequest(id, dto);
    UserDto result = response.getBody().as(UserDto.class);

    Assertions.assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.SC_OK);
    Assertions.assertThat(result)
        .as("Check if updated object is valid")
        .isNotNull()
        .usingRecursiveComparison()
        .isEqualTo(dto);
  }

  @Test
  @DisplayName("PUT request with an invalid id should return 404")
  @Disabled  // not found exception in put method not handled
  void putMethodShouldReturnNotFoundTest() {
    int id = 12;

    Response response = service.putRequest(id, getDto());

    Assertions.assertThat(response.getStatusCode())
        .as("Check if response status code is 404")
        .isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("DELETE method should return a valid response")
  void deleteMethodTest() {
    int id = 2;

    Response response = service.deleteRequest(id);

    Assertions.assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("DELETE method should return 404 for an invalid request")
  @Disabled  // delete of a non-existent resource is not handled
  void deleteMethodShouldReturn404Test() {
    int id = 200;

    Response response = service.deleteRequest(id);

    Assertions.assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  private UserDto getDto() {
    return UserDto.builder()
        .name("name")
        .username("username")
        .email("email")
        .address(new AddressDTO("street", "suite", "city", "zipcode",
            new GeoDTO("lat", "lng")))
        .phone("phone")
        .website("website")
        .company(new CompanyDTO("companyName", "catchPhrase", "bs"))
        .build();
  }
}
