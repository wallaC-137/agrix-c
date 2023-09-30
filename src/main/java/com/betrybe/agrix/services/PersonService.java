package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   */
  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Returns a person for a given ID.
   *
   * @param id the id
   * @return the person by id
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Returns a person for a given username.
   *
   * @param username the username
   * @return the person by username
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> person = personRepository.findByUsername(username);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Creates a new person.
   *
   * @param person the person
   * @return the person
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Gets all persons.
   *
   * @return the all persons
   */
  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }
}
