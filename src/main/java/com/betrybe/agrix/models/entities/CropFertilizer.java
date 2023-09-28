package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Crop fertilizer.
 */
@Entity
@Table(name = "crops_fertilizers")
public class CropFertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "crop_id")
  private Crop cropId;

  @ManyToOne
  @JoinColumn(name = "fertilizer_id")
  private Fertilizer fertilizerId;

  /**
   * Instantiates a new Crop fertilizer.
   */
  public CropFertilizer() {
  }

  /**
   * Instantiates a new Crop fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  @Autowired
  public CropFertilizer(Crop cropId, Fertilizer fertilizerId) {
    this.cropId = cropId;
    this.fertilizerId = fertilizerId;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets crop id.
   *
   * @return the crop id
   */
  public Crop getCropId() {
    return cropId;
  }

  /**
   * Sets crop id.
   *
   * @param cropId the crop id
   */
  public void setCropId(Crop cropId) {
    this.cropId = cropId;
  }

  /**
   * Gets fertilizer id.
   *
   * @return the fertilizer id
   */
  public Fertilizer getFertilizerId() {
    return fertilizerId;
  }

  /**
   * Sets fertilizer id.
   *
   * @param fertilizerId the fertilizer id
   */
  public void setFertilizerId(Fertilizer fertilizerId) {
    this.fertilizerId = fertilizerId;
  }
}
