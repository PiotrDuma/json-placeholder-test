package com.github.PiotrDuma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
  private String street;
  private String suite;
  private String city;
  @JsonProperty("zipcode")
  private String zipCode;
  @JsonProperty("geo")
  private Geolocation geolocation;
}
