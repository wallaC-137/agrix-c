package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, @JsonIgnore String password, Role role) {

  /**
   * To person.
   *
   * @return the person
   */
  public Person toPerson() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;

  }
}
