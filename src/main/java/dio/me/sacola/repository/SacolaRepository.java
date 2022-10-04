package dio.me.sacola.repository;

import dio.me.sacola.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola,Long> {
}
