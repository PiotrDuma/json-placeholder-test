package com.github.PiotrDuma.utils;

import java.util.List;

public interface ResponseDetails {
  int getStatusCode();
  <T> T getResponseObject(Class<T> type);
  <T> List<T> getResponseList(Class<T> type);
  String getContentType();
}
