package com.github.PiotrDuma.utils;

import io.restassured.response.Response;
import java.util.List;

public class ResponseHandler {
  private final Response response;

  public ResponseHandler(Response response){
    this.response = response;
  }

  public ResponseHandler isStatusCodeEqualTo(int statusCode) {
    int result = this.response.getStatusCode();
    if(result != statusCode){
      throw new AssertionError(String.format("Expected response %s, but was %s", statusCode, result));
    }
    return this;
  }

  public <T> T getResponseObject(Class<T> type) {
    return response.getBody().as(type);
  }

  public <T> List<T> getResponseList(Class<T> type) {
    return response.jsonPath().getList("", type);
  }

  public ResponseHandler isContentTypeEqualTo(String contentType) {
    String actual = response.getContentType();
    if(!contentType.equals(actual)){
      throw new AssertionError(String.format("Expected content type %s, but was %s", contentType, actual));
    }
    return this;
  }
}
