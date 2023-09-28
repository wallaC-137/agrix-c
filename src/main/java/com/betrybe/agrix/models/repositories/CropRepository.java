package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {


  /**
   * Find by harvest date between optional.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the optional
   */
  Optional<List<Crop>> findByHarvestDateBetween(LocalDate startDate, LocalDate endDate);
}
