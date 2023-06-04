package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.models.Customer;
import com.sandrapeinados.pelugestion.models.Job;
import com.sandrapeinados.pelugestion.models.SubJob;
import com.sandrapeinados.pelugestion.persistence.entities.CustomerEntity;
import com.sandrapeinados.pelugestion.persistence.entities.JobEntity;
import com.sandrapeinados.pelugestion.persistence.entities.SubJobEntity;
import com.sandrapeinados.pelugestion.persistence.repositories.ICustomerRepository;
import com.sandrapeinados.pelugestion.persistence.repositories.IJobRepository;
import com.sandrapeinados.pelugestion.persistence.repositories.ISubJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepo;
    @Autowired
    private IJobRepository jobRepo;

    @Autowired
    ISubJobRepository subJobRepo;

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setSurname(customer.getSurname());
        customerEntity.setCellphone(customer.getCellphone());
        customerRepo.save(customerEntity);

        customer.setId(customerEntity.getId());
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        List<CustomerEntity> customersFounds = customerRepo.findAll();
        List<Customer> customerList = new ArrayList<>();

        for (CustomerEntity customerEntity : customersFounds) {
            Customer customer = new Customer();
            customer.setId(customerEntity.getId());
            customer.setName(customerEntity.getName());
            customer.setSurname(customerEntity.getSurname());
            customer.setCellphone(customerEntity.getCellphone());

            List<Job> jobsList = new ArrayList<>();
            List<JobEntity> jobsEntity = customerEntity.getJobs();

            for (JobEntity jobEntity : jobsEntity) {
                Job job = new Job();
                job.setIdJob(jobEntity.getJobId());
                job.setJobTitle(jobEntity.getJobTitle());
                job.setJobDescription(jobEntity.getJobDescription());
                job.setTotalAmount(jobEntity.getTotalAmount());
                job.setDate(jobEntity.getDate());

                List<SubJob> subJobsList = new ArrayList<>();
                List<SubJobEntity> subJobsEntity = jobEntity.getSubJobs();

                for (SubJobEntity subJobEntity : subJobsEntity) {
                    SubJob subJob = new SubJob();
                    subJob.setId(subJobEntity.getId());
                    subJob.setSubJobTitle(subJobEntity.getSubJobTitle());
                    subJob.setSubJobAmount(subJobEntity.getSubJobAmount());
                    subJobsList.add(subJob);
                }

                job.setSubJobs(subJobsList);
                jobsList.add(job);
            }

            customer.setJobs(jobsList);
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<CustomerEntity> customerFound = customerRepo.findById(id);
        if (customerFound.isPresent()) {
            CustomerEntity customerEntity = customerFound.get();

            Customer customer = new Customer();
            customer.setId(customerEntity.getId());
            customer.setName(customerEntity.getName());
            customer.setSurname(customerEntity.getSurname());
            customer.setCellphone(customerEntity.getCellphone());

            List<Job> jobsList = new ArrayList<>();
            List<JobEntity> jobsEntity = customerEntity.getJobs();

            for (JobEntity jobEntity : jobsEntity) {
                Job job = new Job();
                job.setIdJob(jobEntity.getJobId());
                job.setJobTitle(jobEntity.getJobTitle());
                job.setJobDescription(jobEntity.getJobDescription());
                job.setTotalAmount(jobEntity.getTotalAmount());
                job.setDate(jobEntity.getDate());

                List<SubJob> subJobsList = new ArrayList<>();
                List<SubJobEntity> subJobsEntity = jobEntity.getSubJobs();

                for (SubJobEntity subJobEntity : subJobsEntity) {
                    SubJob subJob = new SubJob();
                    subJob.setId(subJobEntity.getId());
                    subJob.setSubJobTitle(subJobEntity.getSubJobTitle());
                    subJob.setSubJobAmount(subJobEntity.getSubJobAmount());
                    subJobsList.add(subJob);
                }

                job.setSubJobs(subJobsList);
                jobsList.add(job);
            }

            customer.setJobs(jobsList);

            return customer;
        } else {
            return null;
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        CustomerEntity customerEntity = customerRepo.findById(id).orElseThrow();
        customerRepo.deleteById(customerEntity.getId());
    }
}
