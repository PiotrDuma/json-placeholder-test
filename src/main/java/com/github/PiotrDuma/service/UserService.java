package com.github.PiotrDuma.service;


import com.github.PiotrDuma.model.User;
import com.github.PiotrDuma.utils.ResponseHandler;

public class UserService extends CrudService{
  private static final String URL = "https://jsonplaceholder.typicode.com/users";
  private static final String BY_ID = "/%s";


  public ResponseHandler getUsers(){
    return getRequest(URL);
  }

  public ResponseHandler getUserById(int id){
    return getRequest(getUrl(id));
  }

  public ResponseHandler createUser(User body){
    return postRequest(URL, body);
  }

  public ResponseHandler editUser(int id, User body){
    return putRequest(getUrl(id), body);
  }

  public ResponseHandler deleteUser(int id){
    return deleteRequest(getUrl(id));
  }

  private static String getUrl(int id){
    return String.format(URL + BY_ID, id);
  }
}
