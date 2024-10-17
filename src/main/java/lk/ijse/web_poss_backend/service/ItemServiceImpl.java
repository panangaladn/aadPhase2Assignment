package lk.ijse.web_poss_backend.service;


import lk.ijse.web_poss_backend.customObj.ItemErrorResponse;
import lk.ijse.web_poss_backend.customObj.ItemResponse;
import lk.ijse.web_poss_backend.dao.ItemDao;
import lk.ijse.web_poss_backend.dto.impl.ItemDTO;
import lk.ijse.web_poss_backend.entity.ItemEntity;
import lk.ijse.web_poss_backend.exception.DataPersistFailedException;
import lk.ijse.web_poss_backend.exception.ItemNotFoundException;
import lk.ijse.web_poss_backend.util.AppUtil;
import lk.ijse.web_poss_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

//    @Autowired
    private final ItemDao itemDao;

//    @Autowired
    private final Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        List<String> itemIds = itemDao.findLastItemId();
        String lastItemId = itemIds.isEmpty() ? null : itemIds.get(0);
        itemDTO.setItemId(AppUtil.generateNextItemId(lastItemId));

        ItemEntity saveItem = itemDao.save(mapping.convertToItemEntity(itemDTO));

        if (saveItem == null) {
            throw new DataPersistFailedException("Cannot data saved");
        }
        System.out.println("Saving ItemEntity:"+saveItem.getItemId()+saveItem.getItemName()+saveItem.getItemPrice()+saveItem.getItemQuantity()+saveItem.getItemCategory());
    }

    //Update Item
    @Override
    public void updateItem(ItemDTO itemDTO) {
        Optional<ItemEntity> tmpItem = itemDao.findById(itemDTO.getItemId());
        if (!tmpItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        }else {
            tmpItem.get().setItemName(itemDTO.getItemName());
            tmpItem.get().setItemPrice(itemDTO.getItemPrice());
            tmpItem.get().setItemQuantity(itemDTO.getItemQuantity());
            tmpItem.get().setItemCategory(itemDTO.getItemCategory());
            tmpItem.get().setItemId(itemDTO.getItemId());
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> selectedItemId = itemDao.findById(itemId);
        if (!selectedItemId.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        }else {
            itemDao.deleteById(itemId);
        }
    }

    //Get Item
    @Override
    public ItemResponse getSelectItem(String itemId) {
        if (itemDao.existsById(itemId)) {
            ItemEntity itemEntityByItemId = itemDao.getItemEntityByItemId(itemId);
            return mapping.convertToItemDTO(itemEntityByItemId);
        }else {
            return new ItemErrorResponse(0,"Item not found");
        }
    }

    //Get-All-Items
    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemEntity> getAllItems = itemDao.findAll();
        return mapping.convertItemToDTOList(getAllItems);
    }
}