package org.mvpigs.cotxox;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvpigs.cotxox.domain.Carrera;
import org.mvpigs.cotxox.domain.Conductor;
import org.mvpigs.cotxox.repo.CarreraRepo;
import org.mvpigs.cotxox.repo.ConductorRepo;
import org.mvpigs.cotxox.service.CarreraService;
import org.mvpigs.cotxox.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * Prepara la base de dades per a que:
 * 
 *  En arrancar l'aplicacio no realitzi cap acció 
 * 	sobre l'esquema de base de dades.
 */

@Sql(statements = {
	"drop table t_carreras if exists",
	"drop table t_conductores if exists",
	"create table t_conductores (co_tarjeta_credito varchar(255) not null, co_nombre varchar(255), co_modelo varchar(255), co_matricula varchar(255), co_valoracion_media double, co_ocupado TINYINT,  primary key (co_tarjeta_credito))",
	"create table t_carreras (c_id bigint generated by default as identity, c_tarjeta_credito varchar(255) not null, c_origen varchar(255), c_destino varchar(255), c_distancia double, c_tiempo_esperado integer, c_tiempo_carrera integer, c_coste_total double, c_propina integer, c_conductor varchar(255), primary key (c_id))",
    "alter table t_carreras add constraint carreras_conductor_fk foreign key (c_conductor) references t_conductores",
	"delete from t_carreras",
	"delete from t_conductores",
	"insert into t_conductores (co_tarjeta_credito, co_nombre, co_modelo, co_matricula, co_valoracion_media, co_ocupado) values ('1111111111111111' , 'Samantha', 'Chevy Malibu', '4ABC123', 0, 0)",
	"insert into t_carreras (c_tarjeta_credito, c_origen, c_destino, c_distancia, c_tiempo_esperado, c_tiempo_carrera, c_coste_total, c_propina, c_conductor) values ('4916119711304546', 'Aeroport Son Sant Joan', 'Magaluf', 7.75, 10, 0, 0, 0, '1111111111111111')"			
})

public class CotxoxApplicationTests {

	  @Autowired(required=false)
	  CarreraRepo carreraRepo;

	  @Autowired(required=false)
      CarreraService carreraService;

	  @Autowired(required=false)
	  ConductorRepo conductorRepo;

	  @Autowired(required=false)
	 	ConductorService conductorService;

	@PersistenceContext
	private EntityManager em;

	/** 
	 * TDD 
	 */

	/** 
	 * Mapea la classe conductor per a reflecteixi 
	 * l'esquema de la base de dades 
	 */

	@Test
	public void test_mapping_conductor() {
		Conductor conductor = em.find(Conductor.class, "1111111111111111");
		Assert.assertNotNull(conductor);
		Assert.assertEquals("4ABC123", conductor.getMatricula());
		Assert.assertEquals("Chevy Malibu", conductor.getModelo());
	}


	/**
	 * Crea una classe CarreraRepo que sigui un repositori Spring Data 
	 * per l'entitat Carrera
	 */
	@Test
	public void test_RepoCarerra_es_repositori() { 
		Assert.assertNotNull(carreraRepo);
		Assert.assertTrue(carreraRepo instanceof Repository);
	}


	/**
	 * Crea una classe CarreraService que sigui un component
	 * amb el rol de Service
	 */
	@Test
	public void test_carreraService_es_component() {
		Assert.assertNotNull(carreraService);
	}

    /**
     * Utilitza els mètodes del repositori de carrera
     * i del servei carrera per a fer persistent una carrera
     */

    @Test
    public void test_save_carrera() {
        Long idCarrera = carreraService.crearCarrera("1234567890123456", "Parc de Ses Estacions", "Festival Park", 15, 18);
        // seria necessari afegir el conductor però anem a testear primer repo
        Assert.assertEquals("1234567890123456", carreraService.recuperaCarrera(idCarrera).getTarjetaCredito());
	}
	
	/**
	 * Crea una classe ConductorRepo que sigui un repositori Spring Data 
	 * per l'entitat Conductor
	 */
	@Test
	public void test_ConductorRepo_es_repositori() { 
		Assert.assertNotNull(conductorRepo);
		Assert.assertTrue(conductorRepo instanceof Repository);
	}

	/** 
	 * Implementa el servei de l'entitat conductor i el seu repositori 
	 * per a recuperar un conductor per la seva targeta de crèdit. 
	 */

	@Test 
	public void test_recuperar_conductor() {
		Conductor conductor = conductorService.recuperarConductor("1111111111111111");
		Assert.assertNotNull(conductor);
		Assert.assertEquals("Samantha", conductor.getNombre());
	}

	/** 
	 * Completa el codi del cas test test_save_conductor() 
	 * per a afegir les conductores següents a la BBDD 
	 * mitjançant el servei de l'entitat conductor:
	 * 
	 * String[] nombres = {"Sabrina", "Cici"};
	 * String[] matricula = {"5DHJ444", "7JKK555"};
	 * String[] modelos = {"Toyota Prius", "Mercedes A"};
	 */

	@Test
	public void test_save_conductor() {
		
		/**
		 * Escriu aqui el codi per a crear les conductores 
		 * i escriure-les a la base de dades
		 */

		 Conductor conductorA = new Conductor("2222222222222222");
		 conductorA.setMatricula("5DHJ444");
		 conductorA.setModelo("Toyota Prius");
	
		 Conductor conductorB = new Conductor("3333333333333333");
		 conductorA.setMatricula("7JKK555");
		 conductorA.setModelo("Mercedes A");

		 conductorService.guardaRegistroConductor(conductorA);
		 conductorService.guardaRegistroConductor(conductorB);


		Assert.assertEquals("Sabrina", conductorService.recuperarConductor("2222222222222222").getNombre());
		Assert.assertEquals("Cici", conductorService.recuperarConductor("3333333333333333").getNombre());
		
	}

	/** 
	 * Modifica l'atribut ocupat de l'entitat Conductor i la lògica 
	 * del mètodes setOcupado() i isOcupado() 
	 * per a adaptar-lo al TINYINT de MySQL 
	 */

	@Test
	public void test_BooleanOcupado_adaptado_a_SQL() {
		Conductor conductora = conductorService.recuperarConductor("1111111111111111");
		Assert.assertEquals("Samantha", conductora.getNombre());
		Assert.assertEquals(false, conductora.isOcupado());
		conductora.setOcupado(true);
		Assert.assertEquals(true, conductora.isOcupado());
	}

	/**
	 * Modifica el servei de l'entitat conductor amb un mètode init() per a inserir 
	 * a la base de dades les conductores següents, totes dues desocupades:
	 * String[] nombres = {"Sabrina", "Cici"};
	 * String[] matricula = {"5DHJ444", "7JKK555"};
	 * String[] modelos = {"Toyota Prius", "Mercedes A"} 
	 */

	@Test
	public void test_post_construct_servei_conductor() {
	   
	   conductorService.init();

	   Assert.assertEquals("Sabrina", conductorService.recuperarConductor("2222222222222222").getNombre());
	   Assert.assertEquals(false,conductorService.recuperarConductor("2222222222222222").isOcupado());
	   Assert.assertEquals("Cici", conductorService.recuperarConductor("3333333333333333").getNombre());
	   Assert.assertEquals(false,conductorService.recuperarConductor("3333333333333333").isOcupado());
	}


}