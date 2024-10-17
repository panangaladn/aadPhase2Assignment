package lk.ijse.web_poss_backend.service;

import lk.ijse.web_poss_backend.customObj.ItemResponse;
import lk.ijse.web_poss_backend.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();


    void updateItem(ItemDTO updateItem);

    void deleteItem(String itemId);

    ItemResponse getSelectItem(String itemId);
}