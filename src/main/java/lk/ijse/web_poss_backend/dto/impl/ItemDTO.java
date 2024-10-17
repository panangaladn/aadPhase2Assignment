package lk.ijse.web_poss_backend.dto.impl;

import lk.ijse.web_poss_backend.customObj.ItemResponse;
import lk.ijse.web_poss_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements SuperDTO, ItemResponse{
    private String itemId;
    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String itemCategory;
    private String itemImage;
}
