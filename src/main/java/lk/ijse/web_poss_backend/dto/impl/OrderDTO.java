package lk.ijse.web_poss_backend.dto.impl;

import lk.ijse.web_poss_backend.customObj.OrderResponse;
import lk.ijse.web_poss_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements SuperDTO, OrderResponse {
    private String orderId;
    private String customerId;


    private List<OrderDetailsDTO> orderDetails;
}