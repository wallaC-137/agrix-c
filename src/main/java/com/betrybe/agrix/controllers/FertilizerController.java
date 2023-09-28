package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizer);

    FertilizerDto fertilizerDto = new FertilizerDto(newFertilizer.getId(), newFertilizer.getName(),
        newFertilizer.getBrand(), newFertilizer.getComposition());

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition()))
        .toList();

    return ResponseEntity.ok(fertilizerDtos);
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);

    FertilizerDto fertilizerDto = new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());

    return ResponseEntity.ok(fertilizerDto);
  }
}
