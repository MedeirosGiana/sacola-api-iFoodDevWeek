package dio.me.sacola.repository;

import dio.me.sacola.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ItemRepository extends JpaRepository<Item, Long> {
}
