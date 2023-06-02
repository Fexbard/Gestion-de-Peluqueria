package com.sandrapeinados.pelugestion.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
@Entity
@Table(name = "clients")
public class ClientEntity extends PersonEntity {
    @OneToMany(mappedBy = "clientEntity")
    private List<JobEntity> jobs;

}
