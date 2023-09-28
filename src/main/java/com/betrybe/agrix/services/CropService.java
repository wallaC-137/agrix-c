package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Instantiates a new Farm service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }


  /**
   * Gets all crop.
   *
   * @return the all crop
   */
  public List<Crop> getAllCrop() {

    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  public Crop getCropById(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return crop.get();
  }

  /**
   * Gets crops by date between.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by date between
   */
  public List<Crop> getCropsByDateBetween(LocalDate start, LocalDate end) {
    Optional<List<Crop>> crop = cropRepository.findByHarvestDateBetween(start, end);

    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return crop.get();
  }

  /**
   * Add fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  @Transactional
  public void addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    CropFertilizer cropFertilizer = new CropFertilizer(crop,
        fertilizer);

    //    crop.getCropFertilizers().add(cropFertilizer);
    //    fertilizer.getCropFertilizers().add(cropFertilizer);

    entityManager.persist(cropFertilizer);
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  public Set<CropFertilizer> getFertilizersByCropId(Long cropId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);

    return crop.getCropFertilizers();
  }
  
}
