package tp.models.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import tp.models.entities.servicios.Incidente;
import tp.server.Server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {

  private static RepositorioIncidentes instancia = null;
  private EntityManager entityManager;

  public RepositorioIncidentes(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public static RepositorioIncidentes getInstancia() {
    if (instancia == null) {
      instancia = new RepositorioIncidentes(Server.entityManagerFactory);
    }
    return instancia;
  }

  public Incidente findById(long id) {
    return this.entityManager.find(Incidente.class, id);
  }

  public Incidente findByNombre(String nombre) {
    return (Incidente) this.entityManager
            .createQuery("from " + Incidente.class.getName() + " where nombre = :n")
            .setParameter("n", nombre)
            .getSingleResult();
  }

  public void registrar(Incidente incidente) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.persist(incidente);
    tx.commit();
  }

  public void eliminar(Incidente incidente) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.remove(incidente);
    tx.commit();
  }

  public void actualizar(Incidente incidente){
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.merge(incidente);
    tx.commit();
  }

  public List<Incidente> all() {
    return this.entityManager
            .createQuery("from " + Incidente.class.getName())
            .getResultList();
  }

}
