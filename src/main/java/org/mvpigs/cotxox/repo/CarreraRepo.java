package org.mvpigs.cotxox.repo;

import org.mvpigs.cotxox.domain.Carrera;
import org.springframework.stereotype.Repository;
import org.mvpigs.cotxox.domain.Conductor;
import org.springframework.data.repository.CrudRepository;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.persistence.TypedQuery;
// import java.util.List;
import java.util.Optional;


@Repository
public interface  CarreraRepo extends CrudRepository <Conductor, String> {

    public Carrera save(Carrera carrera);

    public Optional<Carrera> findByTajertaCredito(String tarjetaCredito);

    public Optional<Carrera> findById(Long id);

    // @PersistenceContext
    // EntityManager em;

    // public Long guardarCarrera(Carrera carrera) {

    //     em.persist(carrera);

    //     TypedQuery<Carrera> query = em.createQuery("Select c_id from Carrera carrera where c_destino = :destino AND c_tarjeta_credito = :tarjetaCredito", Carrera.class);
    //     query.setParameter("destino", carrera.getDestino());
    //     query.setParameter("tarjetaCredito", carrera.getTarjetaCredito());

    //     Carrera carreraPersist = query.getSingleResult();
    //     return carreraPersist.getId();
    // }
}
