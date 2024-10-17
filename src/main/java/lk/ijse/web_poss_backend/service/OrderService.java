package lk.ijse.web_poss_backend.service;

import lk.ijse.web_poss_backend.dto.impl.OrderDTO;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
}
