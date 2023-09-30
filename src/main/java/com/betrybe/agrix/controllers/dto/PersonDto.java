package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, @JsonIgnore String password, Role role) {

}
