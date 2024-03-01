package tp.models.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import tp.models.entities.servicios.TipoServicio;
import tp.server.Server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioTiposDeServicio implements WithSimplePersistenceUnit {

  private static RepositorioTiposDeServicio instancia = null;
  private EntityManager entityManager;

  public RepositorioTiposDeServicio(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public static RepositorioTiposDeServicio getInstancia() {
    if (instancia == null) {
      instancia = new RepositorioTiposDeServicio(Server.entityManagerFactory);
    }
    return instancia;
  }

  public TipoServicio findById(long id) {
    return this.entityManager.find(TipoServicio.class, id);
  }

  public TipoServicio findByNombre(String nombre) {
    return (TipoServicio) this.entityManager
            .createQuery("from " + TipoServicio.class.getName() + " where nombre = :n")
            .setParameter("n", nombre)
            .getSingleResult();
  }

  public void registrar(TipoServicio tipoServicio) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.persist(tipoServicio);
    tx.commit();
  }

  public void eliminar(TipoServicio tipoServicio) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.remove(tipoServicio);
    tx.commit();
  }

  public List all() {
    return this.entityManager
            .createQuery("from " + TipoServicio.class.getName())
            .getResultList();
  }
}
