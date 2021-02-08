package com.valdemar.demo.modelo.services.imp;

import com.valdemar.demo.modelo.dao.ProstitutaDao;
import com.valdemar.demo.modelo.documents.Prostituta;
import com.valdemar.demo.modelo.services.ProstitutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProstitutaServiceImpl implements ProstitutaService {
    @Autowired
    private ProstitutaDao prostitutaDao;

    @Override
    public Mono<Prostituta> save(Prostituta prostituta) {
        return prostitutaDao.save(prostituta);
    }

    @Override
    public Flux<Prostituta> findAll() {
        return prostitutaDao.findAll();
    }
}
