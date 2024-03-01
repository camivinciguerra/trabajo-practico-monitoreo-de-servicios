package tp.models.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import tp.models.entities.services.georef.Localidad;
import tp.models.entities.services.georef.Municipio;
import tp.server.Server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioLocalidades implements WithSimplePersistenceUnit {

  private static RepositorioLocalidades instancia = null;
  private EntityManager entityManager;

  public RepositorioLocalidades(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public static RepositorioLocalidades getInstancia() {
    if (instancia == null) {
      instancia = new RepositorioLocalidades(Server.entityManagerFactory);
    }
    return instancia;
  }

  public Localidad findById(long id) {
    return this.entityManager.find(Localidad.class, id);
  }

  public Localidad findByNombre(String nombre) {
    return (Localidad) this.entityManager
            .createQuery("from " + Localidad.class.getName() + " where nombre = :n")
            .setParameter("n", nombre)
            .getSingleResult();
  }

  public void registrar(Localidad localidad) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.persist(localidad);
    tx.commit();
  }

  public void eliminar(Localidad localidad) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.remove(localidad);
    tx.commit();
  }

  public List all() {
    return this.entityManager
            .createQuery("from " + Localidad.class.getName())
            .getResultList();
  }

  public List findByMunicipio(Long idMunicipio) {
    return this.entityManager
            .createQuery("from " + Localidad.class.getName() + " where municipio_id = :municipio_id", Localidad.class)
            .setParameter("municipio_id", idMunicipio)
            .getResultList();
  }
}
