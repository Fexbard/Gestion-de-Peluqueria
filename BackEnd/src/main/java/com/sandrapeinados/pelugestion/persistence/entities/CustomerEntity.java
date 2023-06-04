package com.sandrapeinados.pelugestion.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
@Entity
@Table(name = "customers")
public class CustomerEntity extends PersonEntity {

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobEntity> jobs;

}
