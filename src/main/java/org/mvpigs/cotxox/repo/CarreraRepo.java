package org.mvpigs.cotxox.repo;

import org.springframework.stereotype.Repository;
import org.mvpigs.cotxox.domain.Conductor;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface  CarreraRepo extends CrudRepository <Conductor, String> {

}
