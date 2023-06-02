package com.sandrapeinados.pelugestion.models;

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
public class Job {

    private List<SubJob> subJobs;
    private long idJob;
    @NotBlank(message = "El titulo no puede estar vacío")
    private String jobTitle;
    private String jobDescription;
    @NotBlank
    @Positive(message = "Total debe ser mayor a 0.")
    private double totalAmount;
    private LocalDateTime date;
}
