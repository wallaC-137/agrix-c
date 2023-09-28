package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    return farms.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Optional<Farm>> getFarmById(@PathVariable Long id) {
    Optional<Farm> optionalFarm = farmService.getFarmById(id);

    return ResponseEntity.ok(optionalFarm);
  }

  /**
   * Create crop response entity.
   *
   * @param id      the id
   * @param cropDto the crop dto
   * @return the response entity
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long id, @RequestBody CropDto cropDto) {
    Crop newFarm = farmService.createCrop(id, cropDto.toCrop());

    CropDto cropDto2 = new CropDto(newFarm.getId(), newFarm.getName(), newFarm.getPlantedArea(),
        newFarm.getFarmId().getId(), newFarm.getPlantedDate(), newFarm.getHarvestDate());

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto2);
  }

  /**
   * Gets all crop.
   *
   * @param id the id
   * @return the all crop
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<List<CropDto>> getAllCrop(@PathVariable Long id) {
    List<Crop> crops = farmService.getAllCrop(id);

    List<CropDto> cropDtos = crops.stream()
        .filter((cropF) -> cropF.getFarmId().getId().equals(id))
        .map((crop) -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            id, crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }
}
