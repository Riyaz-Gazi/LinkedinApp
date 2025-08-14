package com.codingshuttle.linkedin.connections_service.service;

import com.codingshuttle.linkedin.connections_service.entity.Person;
import com.codingshuttle.linkedin.connections_service.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionsService {

    private static final Logger log = LoggerFactory.getLogger(ConnectionsService.class);

    private final PersonRepository personRepository;

    public ConnectionsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPerson() {
        System.out.println("Find All");
        return personRepository.findAll();
    }

    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user with id: {}", userId);
        return personRepository.getFirstDegreeConnections(userId);
    }
}
