package org.mvpigs.cotxox.repo;

import java.util.Optional;

import org.mvpigs.cotxox.domain.Carrera;
import org.mvpigs.cotxox.domain.Conductor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepo extends CrudRepository<Conductor, String>  {

    public Conductor save(Conductor carrera);

    public Optional<Conductor> findById(String tarjetaCredito);
}
