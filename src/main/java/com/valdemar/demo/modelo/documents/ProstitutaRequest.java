package com.valdemar.demo.modelo.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProstitutaRequest {
    private String pseudoNombre;
    private Boolean estado;
    private Double tarifa;

}
