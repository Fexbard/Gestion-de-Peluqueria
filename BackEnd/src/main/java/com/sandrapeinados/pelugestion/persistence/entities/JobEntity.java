package com.sandrapeinados.pelugestion.persistence.entities;

import com.sandrapeinados.pelugestion.models.SubJob;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class JobEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long jobId;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "date")
    private LocalDateTime date;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL) //
    private List<SubJobEntity> subJobs;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

}
