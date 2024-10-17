package lk.ijse.web_poss_backend.dto.impl;

import lk.ijse.web_poss_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO implements SuperDTO {
    private String itemId;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private double totalPrice;
    private int discount;
}
