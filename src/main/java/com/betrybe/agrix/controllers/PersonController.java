package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.NewPersonDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Gets persons.
   *
   * @param newPersonDto the new person dto
   * @return the persons
   */
  @PostMapping()
  public ResponseEntity<PersonDto> getPersons(@RequestBody NewPersonDto newPersonDto) {
    Person person = personService.create(newPersonDto.toPerson());

    PersonDto response = new PersonDto(person.getId(), person.getUsername(), person.getPassword(),
        person.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Gets persons.
   *
   * @return the persons
   */
  @GetMapping()
  public ResponseEntity<List<Person>> getPersons() {
    List<Person> persons = personService.getAllPersons();

    return ResponseEntity.status(HttpStatus.OK).body(persons);
  }
}
