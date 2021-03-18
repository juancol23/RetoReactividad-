package com.valdemar.demo.modelo.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prostituta")
public class Prostituta {
	
	@Id
	private String id;
	private String pseudoNombre;
	private Boolean estado;
	private Double tarifa;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	public Prostituta(String pseudoNombre, Boolean estado, Double tarifa) {
		this.pseudoNombre = pseudoNombre;
		this.estado = estado;
		this.tarifa = tarifa;
	}
}