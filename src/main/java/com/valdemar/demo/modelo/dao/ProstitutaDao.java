package com.valdemar.demo.modelo.dao;

import com.valdemar.demo.modelo.entidad.Prostituta;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProstitutaDao extends R2dbcRepository<Prostituta,String> {

}
