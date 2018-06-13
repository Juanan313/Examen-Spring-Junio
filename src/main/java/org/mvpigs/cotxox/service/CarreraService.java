package org.mvpigs.cotxox.service;

import org.mvpigs.cotxox.domain.Carrera;
import org.mvpigs.cotxox.repo.CarreraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    CarreraRepo carreraRepo;

    public Long crearCarrera( String tarjetaCredito, String origen, String destino, int distancia, int tiempoEsperado  ) {

        Carrera carrera = new Carrera(tarjetaCredito);

        carreraRepo.save(carrera);

        Optional<Carrera> carreraPersist = carreraRepo.findByTajertaCredito(tarjetaCredito);

        return carreraPersist.get().getId();
    }

    public Carrera recuperaCarrera(Long id) {
        
        Optional<Carrera> carrera = carreraRepo.findById(id);
        return carrera.get();
    }

    public Carrera updateCarrera(Carrera carrera) {
        return carreraRepo.save(carrera);
    }

}