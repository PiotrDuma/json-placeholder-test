package com.github.PiotrDuma.service;

import com.github.PiotrDuma.model.User;
import com.github.PiotrDuma.utils.ResponseHandler;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

class CrudService {
  private static final String HEADER_CONTENT = "application/json; charset=utf-8";

  private final RequestSpecification request;

  protected CrudService() {
    this.request = RestAssured.given()
        .filter(new RequestLoggingFilter(LogDetail.ALL, true, System.out))
        .filter(new ResponseLoggingFilter(LogDetail.ALL, true, System.out))
        .header("Content-Type", HEADER_CONTENT)
        .header("accept", HEADER_CONTENT);
  }

  protected ResponseHandler getRequest(String url){
    return new ResponseHandler(request.get(url));
  }

  protected ResponseHandler postRequest(String url, User body){
    return new ResponseHandler(request.body(body).post(url));
  }

  protected ResponseHandler putRequest(String url, User body){
    return new ResponseHandler(request.body(body).put(url));
  }

  protected ResponseHandler deleteRequest(String url){
    return new ResponseHandler(request.delete(url));
  }
}
