package com.valdemar.demo.modelo.dao;

import com.valdemar.demo.modelo.documents.Prostituta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProstitutaDao extends ReactiveMongoRepository<Prostituta,String> {

}
