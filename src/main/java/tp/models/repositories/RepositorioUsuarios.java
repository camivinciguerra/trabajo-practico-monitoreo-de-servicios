package tp.models.repositories;

import tp.models.entities.comunidad.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import tp.server.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

  private static RepositorioUsuarios instancia = null;
  private EntityManager entityManager;

  public RepositorioUsuarios(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public static RepositorioUsuarios getInstancia() {
    if (instancia == null) {
      instancia = new RepositorioUsuarios(Server.entityManagerFactory);
    }
    return instancia;
  }

  public Usuario findById(long id) {
    return this.entityManager.find(Usuario.class, id);
  }

  public Usuario findByNombre(String nombre) {
    Usuario usuario;
    try{
      usuario = (Usuario) this.entityManager
              .createQuery("from " + Usuario.class.getName() + " where nombreUsuario = :n")
              .setParameter("n", nombre)
              .getSingleResult();
    }
    catch(NoResultException exception){
      usuario = null;
    }

    return usuario;
  }

  public void registrar(Usuario usuario) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.persist(usuario);
    tx.commit();
  }

  public void eliminar(Usuario usuario) {
    EntityTransaction tx = this.entityManager.getTransaction();
    tx.begin();
    this.entityManager.remove(usuario);
    tx.commit();
  }

  public List all() {
    return this.entityManager
            .createQuery("from " + Usuario.class.getName())
            .getResultList();
  }
}
