package lk.ijse.web_poss_backend.dao;

import lk.ijse.web_poss_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {

    @Query("SELECT i.orderId FROM OrderEntity i ORDER BY i.orderId DESC")
    List<String> findLastOrderId();
}
