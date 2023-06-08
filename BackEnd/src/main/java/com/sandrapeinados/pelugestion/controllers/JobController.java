package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.services.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private IJobService jobService;

    @PostMapping
    public ResponseEntity<?> saveJob(@RequestBody Job job) {
        jobService.saveJob(job);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(job.getIdJob())
                .toUri();

        return ResponseEntity.created(location).body(job);
    }
    @GetMapping
    public ResponseEntity<?> getJobs(){
        List<Job> jobList = jobService.getAllJobs();
        return ResponseEntity.ok(jobList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<?> updateJob(@RequestBody Job job){
        jobService.updateJob(job);
        return ResponseEntity.ok(job);
    }

}
