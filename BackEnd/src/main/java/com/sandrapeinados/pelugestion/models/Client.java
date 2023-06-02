package com.sandrapeinados.pelugestion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person {

    private List<Job> jobs;

}
