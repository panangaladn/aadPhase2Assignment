package lk.ijse.web_poss_backend.dao;

import lk.ijse.web_poss_backend.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetailsEntity, String> {
}
