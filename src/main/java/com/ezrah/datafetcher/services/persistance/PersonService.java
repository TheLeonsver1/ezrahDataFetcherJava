package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Person;
import com.ezrah.datafetcher.persistence.entities.PersonPosition;

import java.util.Optional;

public interface PersonService {
    Optional<Person> getPersonByKnsId(Integer knsId);

    Optional<PersonPosition> getPersonPositionByKnsId(Integer knsId);

    Person savePerson(Person person);

    PersonPosition savePersonPosition(PersonPosition personPosition);

    long countPersons();

}
