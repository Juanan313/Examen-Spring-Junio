package org.mvpigs.cotxox.service;


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

    public void guardaRegistroConductor(Conductor conductor){
        conductorRepo.save(conductor);
    };

    public void init() {

        Conductor conductorA = new Conductor("2222222222222222");
        conductorA.setMatricula("5DHJ444");
        conductorA.setModelo("Toyota Prius");
        conductorA.setOcupado(false);
        
        Conductor conductorB = new Conductor("3333333333333333");
        conductorA.setMatricula("7JKK555");
        conductorA.setModelo("Mercedes A");
        conductorB.setOcupado(false);

        guardaRegistroConductor(conductorA);
        guardaRegistroConductor(conductorB);

    }
}
