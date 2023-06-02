package com.sandrapeinados.pelugestion.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "assistants")
public class AssistantEntity extends PersonEntity {

}
