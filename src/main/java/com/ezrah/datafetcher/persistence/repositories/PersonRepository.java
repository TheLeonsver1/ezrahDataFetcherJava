package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
    Optional<Person> findByKnsId(Integer knsPersonId);

}
