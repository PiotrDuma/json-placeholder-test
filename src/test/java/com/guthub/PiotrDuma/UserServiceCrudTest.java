package com.guthub.PiotrDuma;

import com.github.PiotrDuma.model.Address;
import com.github.PiotrDuma.model.Company;
import com.github.PiotrDuma.model.Geolocation;
import com.github.PiotrDuma.model.User;
import com.github.PiotrDuma.service.UserService;
import com.github.PiotrDuma.utils.ResponseHandler;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceCrudTest {

  private UserService service;

  @BeforeEach
  void setUp() {
    this.service = new UserService();
  }

  @Test
  @DisplayName("GET by id should return an object")
  void getMethodShouldReturnByIdTest() {
    int id = 1;

    User responseObject = service.getUserById(id)
        .isStatusCodeEqualTo(HttpStatus.SC_OK)
        .getResponseObject(User.class);

    Assertions.assertThat(responseObject)
        .as("Check if received object has valid id")
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", id);
  }

  @Test
  @DisplayName("GET by invalid id should return 404")
  void getMethodShouldReturnNotFoundTest() {
    int id = 12;

    ResponseHandler response = service.getUserById(id)
        .isStatusCodeEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("POST request should create valid object")
  void postMethodShouldReturnByIdTest() {
    User user = getUser();

    User responseObject = service.createUser(user)
        .isStatusCodeEqualTo(HttpStatus.SC_CREATED)
        .getResponseObject(User.class);

    Assertions.assertThat(responseObject)
        .as("Check if created object is valid")
        .isNotNull()
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(user);
  }

  @Test
  @DisplayName("PUT request should return updated object")
  void putMethodShouldReturnByIdTest() {
    int id = 2;
    User user = getUser();
    user.setId(2);

    User responseObject = service.editUser(id, user)
        .isStatusCodeEqualTo(HttpStatus.SC_OK)
        .getResponseObject(User.class);

    Assertions.assertThat(responseObject)
        .as("Check if updated object is valid")
        .isNotNull()
        .usingRecursiveComparison()
        .isEqualTo(user);
  }

  @Test
  @DisplayName("PUT request with an invalid id should return 404")
  @Disabled  // not found exception in put method not handled
  void putMethodShouldReturnNotFoundTest() {
    int id = 12;

    service.editUser(id, getUser())
        .isStatusCodeEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("DELETE method should return a valid response")
  void deleteMethodTest() {
    int id = 2;

    service.deleteUser(id)
        .isStatusCodeEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("DELETE method should return 404 for an invalid request")
  @Disabled  // delete of a non-existent resource is not handled
  void deleteMethodShouldReturn404Test() {
    int id = 200;

    service.deleteUser(id)
        .isStatusCodeEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  private User getUser() {
    return User.builder()
        .name("name")
        .username("username")
        .email("email")
        .address(new Address("street", "suite", "city", "zipcode",
            new Geolocation("lat", "lng")))
        .phone("phone")
        .website("website")
        .company(new Company("companyName", "catchPhrase", "bs"))
        .build();
  }
}
