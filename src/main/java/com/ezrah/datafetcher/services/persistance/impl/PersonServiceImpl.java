package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Person;
import com.ezrah.datafetcher.persistence.entities.PersonPosition;
import com.ezrah.datafetcher.persistence.repositories.PersonRepository;
import com.ezrah.datafetcher.persistence.repositories.PersonToPositionRepo;
import com.ezrah.datafetcher.services.persistance.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    private final PersonToPositionRepo personToPositionRepo;

    private final FactionService factionService;

    private final CommitteeService committeeService;

    private final MinistryService ministryService;

    private final PositionService positionService;

    @Override
    public Optional<Person> getPersonByKnsId(Integer knsId) {
        return personRepository.findByKnsId(knsId);
    }

    @Override
    public Optional<PersonPosition> getPersonPositionByKnsId(Integer knsId) {
        return personToPositionRepo.findByKnsId(knsId);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public PersonPosition savePersonPosition(PersonPosition personPosition) {
        var mappedPersonSearchResult = getPersonByKnsId(personPosition.getKnsPersonId());
        if (mappedPersonSearchResult.isPresent()) {
            personPosition.setPerson(mappedPersonSearchResult.get());
        } else {
            log.error("Couldn't find mapped person in person to position {}", personPosition);
        }

        positionService.getByKnsId(personPosition.getKnsPositionId())
                .ifPresent(personPosition::setPosition);
        factionService.getFactionPerKnessetByKnsId(personPosition.getKnsFactionId())
                .ifPresent(personPosition::setFactionPersonWasIn);
        ministryService.findByKnsId(personPosition.getKnsGovMinistryId())
                .ifPresent(personPosition::setMinistryPersonWasIn);
        committeeService.findByKnsId(personPosition.getKnsCommitteeId())
                .ifPresent(personPosition::setCommitteePersonWasIn);

        return personToPositionRepo.save(personPosition);

    }

    @Override
    public long countPersons() {
        return 0;
    }
}
