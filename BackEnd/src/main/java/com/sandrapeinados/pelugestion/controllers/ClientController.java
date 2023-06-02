package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.Client;
import com.sandrapeinados.pelugestion.services.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;
    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody @Valid Client client){
        Client clientSaved = clientService.saveClient(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(client);
    }
}
