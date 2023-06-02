package com.sandrapeinados.pelugestion.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subJob")
public class SubJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subJob_id")
    private long id;
    @Column(name = "subJob_title")
    private String subJobTitle;
    @Column(name = "amount")
    private double subJobAmount;

    @ManyToOne
    @JoinColumn(name = "job_id")
    JobEntity job;

}
