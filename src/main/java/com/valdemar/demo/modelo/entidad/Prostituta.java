package com.valdemar.demo.modelo.entidad;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("prostituta")
public class Prostituta {
	
	@Id
	private String id;
	@Column("pseudoNombre")
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


