package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Person JPA repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  /**
   * Find by username optional.
   *
   * @param username the username
   * @return the optional
   */
  Optional<Person> findByUsername(String username);
}
