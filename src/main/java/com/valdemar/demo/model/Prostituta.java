package com.valdemar.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Prostituta implements Serializable {
    private String id;
    private String pseudoNombre;
    private Boolean estado;
    private Double tarifa;
}
