package tp.models.entities.ranking;

  import lombok.Getter;
  import tp.models.repositories.RepositorioEntidades;
  import java.util.List;

public class Ranking {

  @Getter
  private List<Criterio> criteriosParaRanking;

  public void rankear(){
    this.getCriteriosParaRanking().forEach(unCriterio->unCriterio.generarReporte(RepositorioEntidades.getInstancia().all()));
  }
}
