package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.CropFertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;


  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crop.
   *
   * @return the all crop
   */
  @Secured({"ADMIN", "MANAGER"})
  @GetMapping()
  public ResponseEntity<List<CropDto>> getAllCrop() {
    List<Crop> crops = cropService.getAllCrop();

    List<CropDto> cropDtos = crops.stream()
        .map((crop) -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarmId().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);

    CropDto cropDto = new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getFarmId().getId(), crop.getPlantedDate(), crop.getHarvestDate());

    return ResponseEntity.ok(cropDto);
  }


  /**
   * Gets crops by harvest date.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the crops by harvest date
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
      @RequestParam("start") LocalDate startDate,
      @RequestParam("end") LocalDate endDate) {

    List<Crop> crops = cropService.getCropsByDateBetween(startDate, endDate);

    List<CropDto> cropDtos = crops.stream()
        .map((crop) -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarmId().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Add fertilizer to crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.addFertilizerToCrop(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<CropFertilizerDto>> getFertilizersByCropId(@PathVariable Long cropId) {
    Set<CropFertilizer> fertilizers = cropService.getFertilizersByCropId(cropId);

    List<CropFertilizerDto> cropFertilizerDto = fertilizers.stream()
        .map(fertilizer -> new CropFertilizerDto(fertilizer.getFertilizerId().getId(),
            fertilizer.getFertilizerId().getName(), fertilizer.getFertilizerId().getBrand(),
            fertilizer.getFertilizerId().getComposition())).toList();

    return ResponseEntity.ok(cropFertilizerDto);
  }

}
