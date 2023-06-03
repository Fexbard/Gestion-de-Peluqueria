package com.sandrapeinados.pelugestion.services;


import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.models.SubJob;

import java.util.List;


public interface IJobService {

    Job saveJob(Job job, Long id);

}
