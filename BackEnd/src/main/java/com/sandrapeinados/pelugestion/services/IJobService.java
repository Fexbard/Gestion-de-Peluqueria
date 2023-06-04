package com.sandrapeinados.pelugestion.services;


import com.sandrapeinados.pelugestion.models.Job;


public interface IJobService {

    Job saveJob(Job job, Long id);

}
