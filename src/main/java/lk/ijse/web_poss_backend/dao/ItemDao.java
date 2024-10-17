package lk.ijse.web_poss_backend.dao;

import lk.ijse.web_poss_backend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity,String> {

    ItemEntity getItemEntityByItemId(String itemId);

    @Query("SELECT i.itemId FROM ItemEntity i ORDER BY i.itemId DESC")
    List<String> findLastItemId();
}










//    @Query("SELECT i.itemId FROM ItemEntity i ORDER BY i.itemId DESC")
//    String findLastItemId();