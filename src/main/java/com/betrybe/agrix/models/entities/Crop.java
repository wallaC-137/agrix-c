package com.betrybe.agrix.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //  private Long farmId; //não sei se é necessário
  private String name;
  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;


  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farmId;

  @OneToMany(mappedBy = "cropId", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<CropFertilizer> cropFertilizers;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {
  }

  /**
   * Instantiates a new Crop.
   *
   * @param id              the id
   * @param name            the name
   * @param plantedArea     the planted area
   * @param farmId          the farm id
   * @param plantedDate     the planted date
   * @param harvestDate     the harvest date
   * @param cropFertilizers the crop fertilizers
   */
  @Autowired
  public Crop(Long id, String name, Double plantedArea, Farm farmId, LocalDate plantedDate,
      LocalDate harvestDate, Set<CropFertilizer> cropFertilizers) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.farmId = farmId;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
    this.cropFertilizers = cropFertilizers;
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
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets planted area.
   *
   * @return the planted area
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Sets planted area.
   *
   * @param plantedArea the planted area
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Gets farm id.
   *
   * @return the farm id
   */
  public Farm getFarmId() {
    return farmId;
  }

  /**
   * Sets farm id.
   *
   * @param farm the farm
   */
  public void setFarmId(Farm farm) {
    this.farmId = farm;
  }

  /**
   * Gets planted date.
   *
   * @return the planted date
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Sets planted date.
   *
   * @param plantedDate the planted date
   */
  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  /**
   * Gets harvest date.
   *
   * @return the harvest date
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  /**
   * Sets harvest date.
   *
   * @param harvestDate the harvest date
   */
  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  /**
   * Gets crop fertilizers.
   *
   * @return the crop fertilizers
   */
  public Set<CropFertilizer> getCropFertilizers() {
    return cropFertilizers;
  }

  /**
   * Sets crop fertilizers.
   *
   * @param cropFertilizers the crop fertilizers
   */
  public void setCropFertilizers(
      Set<CropFertilizer> cropFertilizers) {
    this.cropFertilizers = cropFertilizers;
  }
}
