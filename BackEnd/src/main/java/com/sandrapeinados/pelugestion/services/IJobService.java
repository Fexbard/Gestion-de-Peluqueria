package com.sandrapeinados.pelugestion.services;


import com.sandrapeinados.pelugestion.models.Job;

import java.util.List;


public interface IJobService {
    Job saveJob(Job job);
    void deleteJob(Long id);

    Job getJobById(Long id);

    List<Job> getAllJobs();
}
