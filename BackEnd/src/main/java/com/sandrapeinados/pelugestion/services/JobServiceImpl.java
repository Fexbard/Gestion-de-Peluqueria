package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.models.Client;
import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.models.SubJob;
import com.sandrapeinados.pelugestion.persistence.entities.ClientEntity;
import com.sandrapeinados.pelugestion.persistence.entities.JobEntity;
import com.sandrapeinados.pelugestion.persistence.entities.SubJobEntity;
import com.sandrapeinados.pelugestion.persistence.repositories.IJobRepository;
import com.sandrapeinados.pelugestion.persistence.repositories.ISubJobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private IJobRepository jobRepo;
    @Autowired
    private ISubJobRepository subJobRepo;
    @Override
    public Job saveJob(Job job, Long id) {
        //Traer el cliente con el get clientById, ahora hago el new client asi no mas porque no tengo el metodo getclientbyid
        Client client = new Client();
        client.setId(id);
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());

        List<SubJob> list = job.getSubJobs();
        List<SubJobEntity> listSubJobsEntity = new ArrayList<>();
        double totalAmount = 0;
        //Convierte la lista de SubJob a SubJobEntity y va sumando los montos
        for (SubJob s:  list){
            SubJobEntity subJobEntity = new SubJobEntity();
            subJobEntity.setSubJobTitle(s.getSubJobTitle());
            subJobEntity.setSubJobAmount(s.getSubJobAmount());
            listSubJobsEntity.add(subJobEntity);
            totalAmount += subJobEntity.getSubJobAmount();
        }


        JobEntity jobToSave = new JobEntity();

        jobToSave.setJobTitle(job.getJobTitle());
        jobToSave.setJobDescription(job.getJobDescription());
        jobToSave.setTotalAmount(totalAmount);
        jobToSave.setDate(job.getDate());
        jobToSave.setClientEntity(clientEntity);
        //Se guarda primero el Job sin la lista de SubJobs porque necesita el Id del Job y JPA no lo está tomando
        jobRepo.save(jobToSave);

        //Agrega el Job a las SubJobs
        for (SubJobEntity s:  listSubJobsEntity){
            s.setJob(jobToSave);
        }
        //Se guarda la lista de SubJobs correctamente con el Id del Job que las vincula
        subJobRepo.saveAll(listSubJobsEntity);

        //Setea el Id de cada SubJob
        for(int i = 0; i < listSubJobsEntity.size(); i++){
            list.get(i).setId(listSubJobsEntity.get(i).getId());
        }

        job.setIdJob(jobToSave.getJobId());
        job.setSubJobs(list);

        return job;
    }

}
