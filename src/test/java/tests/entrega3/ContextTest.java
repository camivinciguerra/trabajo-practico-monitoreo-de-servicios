package tests.entrega3;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Test;

public class ContextTest implements SimplePersistenceTest {

  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  void contextUpWithTransaction() throws Exception {
    withTransaction(() -> {
    });
  }
}
