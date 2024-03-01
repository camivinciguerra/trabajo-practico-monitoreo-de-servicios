package tests.entrega3;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.models.entities.services.georef.Municipio;
import tp.models.repositories.RepositorioMunicipios;
import tp.models.repositories.RepositorioProvincias;
import tp.models.entities.services.georef.Provincia;

import java.util.List;

public class Entrega3Tests implements SimplePersistenceTest {

  private RepositorioProvincias repositorioProvincias;

  @BeforeEach
  public void init() {
    this.repositorioProvincias = RepositorioProvincias.getInstancia();
  }
  @Test
  public void buscaProvinciaPorId() {
    Assertions.assertEquals("Buenos Aires",
        this.repositorioProvincias.findById(6L).getNombre());
  }

  @Test
  public void eliminaProvincia() {
    Assertions.assertEquals(24, this.repositorioProvincias.all().size());

    Provincia caba = this.repositorioProvincias.findById(2L);
    this.repositorioProvincias.eliminar(caba);
    Assertions.assertEquals(23, this.repositorioProvincias.all().size());
  }
  @Test
  public void buscarMunicipiosDeBSAS(){
  List<Municipio> municipios = RepositorioMunicipios.getInstancia().findByProvincia(Long.valueOf(String.valueOf(10)));
  for (Municipio municipio: municipios){
      System.out.println(municipio.nombre);
    }
  }
}
