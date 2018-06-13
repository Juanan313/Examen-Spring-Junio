package org.mvpigs.cotxox.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.mvpigs.cotxox.domain.Conductor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepo extends CrudRepository<Conductor, String>  {

    public Conductor save(Conductor conductor);

    public Optional<Conductor> findById(String tarjetaCredito);

    @Query("select conductor from Conductor conductor where conductor.ocupado = ?1")
    public ArrayList<Conductor> findByOcupado(int ocupado);
}
