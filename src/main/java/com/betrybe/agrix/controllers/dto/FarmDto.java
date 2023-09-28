package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * To farm.
   *
   * @return the farm
   */
  public Farm toFarm() {
    return new Farm(id, name, size, null);
  }
}
