package com.github.PiotrDuma.service;

import com.github.PiotrDuma.model.UserDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonService {
  private static final String URL = "https://jsonplaceholder.typicode.com/users";
  private static final String BY_ID = "/%s";
  private static final String HEADER_CONTENT = "application/json; charset=utf-8";

  private final RequestSpecification request;

  public JsonService() {
    this.request = RestAssured.given()
        .header("Content-Type", HEADER_CONTENT)
        .header("accept", HEADER_CONTENT);
  }

  public Response getRequest(){
    return request.get(URL);
  }

  public Response getRequest(int id){
    return request.get(getUrl(id));
  }

  public Response postRequest(UserDto dto){
    return request.body(dto).post(URL);
  }

  public Response putRequest(int id, UserDto dto){
    return request.body(dto).put(getUrl(id));
  }

  public Response deleteRequest(int id){
    return request.delete(getUrl(id));
  }

  private static String getUrl(int id){
    return String.format(URL + BY_ID, id);
  }
}
