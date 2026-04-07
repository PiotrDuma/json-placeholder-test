package com.github.PiotrDuma.utils;

import io.restassured.response.Response;
import java.util.List;

public class ResponseDetailsWrapper implements ResponseDetails{
  private final Response response;

  public ResponseDetailsWrapper(Response response){
    this.response = response;
  }

  @Override
  public int getStatusCode() {
    return response.getStatusCode();
  }

  @Override
  public <T> T getResponseObject(Class<T> type) {
    return response.getBody().as(type);
  }

  @Override
  public <T> List<T> getResponseList(Class<T> type) {
    return response.jsonPath().getList("", type);
  }

  @Override
  public String getContentType() {
    return response.getContentType();
  }
}
