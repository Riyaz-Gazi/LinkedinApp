package com.codingshuttle.linkedin.connections_service.controller;

import com.codingshuttle.linkedin.connections_service.entity.Person;
import com.codingshuttle.linkedin.connections_service.service.ConnectionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPerson() {
        return ResponseEntity.ok(connectionsService.findAllPerson());
    }
}
