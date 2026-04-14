package com.github.PiotrDuma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  private int id;
  private String name;
  private String username;
  private String email;
  private AddressDTO address;
  private String phone;
  private String website;
  private CompanyDTO company;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class AddressDTO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geo;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class GeoDTO {
    private String lat;
    private String lng;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class CompanyDTO {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}