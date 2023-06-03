package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.Client;
import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.models.SubJob;
import com.sandrapeinados.pelugestion.services.IClientService;
import com.sandrapeinados.pelugestion.services.IJobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @Autowired
    private IJobService jobService;

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody @Valid Client client) {
        Client clientSaved = clientService.saveClient(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PostMapping("/{id}/jobs")
    public ResponseEntity<?> saveJob(@PathVariable Long id, @RequestBody Job job) {

        Job jobSaved = jobService.saveJob(job, id);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idJob}")
                .buildAndExpand(jobSaved.getIdJob())
                .toUri();

        return ResponseEntity.created(location).body(job);

    }

}
