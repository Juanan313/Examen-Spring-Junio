package org.mvpigs.cotxox.service;

import java.util.Optional;

import org.mvpigs.cotxox.domain.Conductor;
import org.mvpigs.cotxox.repo.ConductorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConductorService {

    @Autowired
    ConductorRepo conductorRepo;

    public Conductor recuperarConductor(String tarjetaCredito) {
        return conductorRepo.findById(tarjetaCredito).get();
    }
}
