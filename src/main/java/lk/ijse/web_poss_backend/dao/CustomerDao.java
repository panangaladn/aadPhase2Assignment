package lk.ijse.web_poss_backend.dao;

import lk.ijse.web_poss_backend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,String> {

    @Query("SELECT i.customerId FROM CustomerEntity i ORDER BY i.customerId DESC")
    List<String> findLastCustomerId();
}