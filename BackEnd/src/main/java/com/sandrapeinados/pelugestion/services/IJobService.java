package com.sandrapeinados.pelugestion.services;


import com.sandrapeinados.pelugestion.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IJobService {
    Job saveJob(Job job);
    void deleteJob(Long id);
    Job getJobById(Long id);
    List<Job> getAllJobs();
    Job updateJob(Job job);
    Page<Job> getJobsPaged(Pageable pageable);
}
