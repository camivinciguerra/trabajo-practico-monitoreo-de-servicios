package tp.models.entities.ranking;

import org.springframework.scheduling.annotation.Scheduled;

public class ProgramadorRanking {

  Ranking ranking;

  @Scheduled(cron = "0 14 19 ? MON")
  public void ejecutar(){
    ranking.rankear();
  }
}
