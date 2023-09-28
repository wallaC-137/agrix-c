package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Insert farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  public Optional<Farm> getFarmById(Long id) {
    Optional<Farm> farm = farmRepository.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    return farm;
  }

  /**
   * Create crop crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   */
  public Crop createCrop(Long id, Crop crop) {
    Optional<Farm> farm = farmRepository.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    crop.setFarmId(farm.get());

    return cropRepository.save(crop);
  }

  /**
   * Gets all crop.
   *
   * @param id the id
   * @return the all crop
   */
  public List<Crop> getAllCrop(Long id) {
    Optional<Farm> farm = farmRepository.findById(id);
    List<Crop> crops = cropRepository.findAll();

    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return crops;
  }
}
