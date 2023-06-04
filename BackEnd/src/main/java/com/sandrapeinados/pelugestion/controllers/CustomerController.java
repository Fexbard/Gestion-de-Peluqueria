package com.sandrapeinados.pelugestion.controllers;

import com.sandrapeinados.pelugestion.models.Customer;
import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.services.ICustomerService;
import com.sandrapeinados.pelugestion.services.IJobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IJobService jobService;
    @GetMapping
    public ResponseEntity<?> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        Customer customerFound = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerFound);
    }
    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid Customer customer) {
        Customer customerSaved = customerService.saveCustomer(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
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
