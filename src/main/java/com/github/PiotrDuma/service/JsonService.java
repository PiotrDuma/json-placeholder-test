package com.github.PiotrDuma.service;

import com.github.PiotrDuma.model.UserDto;
import com.github.PiotrDuma.utils.ResponseDetails;
import com.github.PiotrDuma.utils.ResponseDetailsWrapper;
import io.restassured.RestAssured;
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

  public ResponseDetails getRequest(){
    return new ResponseDetailsWrapper(request.get(URL));
  }

  public ResponseDetails getRequest(int id){
    return new ResponseDetailsWrapper(request.get(getUrl(id)));
  }

  public ResponseDetails postRequest(UserDto dto){
    return new ResponseDetailsWrapper(request.body(dto).post(URL));
  }

  public ResponseDetails putRequest(int id, UserDto dto){
    return new ResponseDetailsWrapper(request.body(dto).put(getUrl(id)));
  }

  public ResponseDetails deleteRequest(int id){
    return new ResponseDetailsWrapper(request.delete(getUrl(id)));
  }

  private static String getUrl(int id){
    return String.format(URL + BY_ID, id);
  }
}
