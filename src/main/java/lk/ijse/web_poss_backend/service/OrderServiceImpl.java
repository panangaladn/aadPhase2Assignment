package lk.ijse.web_poss_backend.service;


import jakarta.transaction.Transactional;
import lk.ijse.web_poss_backend.dao.CustomerDao;
import lk.ijse.web_poss_backend.dao.ItemDao;
import lk.ijse.web_poss_backend.dao.OrderDao;
import lk.ijse.web_poss_backend.dao.OrderDetailsDao;
import lk.ijse.web_poss_backend.dto.impl.OrderDTO;
import lk.ijse.web_poss_backend.dto.impl.OrderDetailsDTO;
import lk.ijse.web_poss_backend.embedded.OrderDetailsPK;
import lk.ijse.web_poss_backend.entity.CustomerEntity;
import lk.ijse.web_poss_backend.entity.ItemEntity;
import lk.ijse.web_poss_backend.entity.OrderDetailsEntity;
import lk.ijse.web_poss_backend.entity.OrderEntity;
import lk.ijse.web_poss_backend.util.AppUtil;
import lk.ijse.web_poss_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private final ItemDao itemDao;
//    private final OrderDetailsDao orderDetailsDao;
//    private Mapping mapping;


    @Override
    public void saveOrder(OrderDTO orderDTO) {
        try {
            List<String> orderIds = orderDao.findLastOrderId();
            String lastOrderId = orderIds.isEmpty() ? null : orderIds.get(0);
            orderDTO.setOrderId(AppUtil.generateNextOrderId(lastOrderId));

            CustomerEntity customer = customerDao.findById(orderDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(orderDTO.getOrderId());
            orderEntity.setCustomer(customer);

            List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
            double totalOrderPrice = 0.0;

            for (OrderDetailsDTO orderDetailsDTO : orderDTO.getOrderDetails()) {
                ItemEntity item = itemDao.findById(orderDetailsDTO.getItemId())
                        .orElseThrow(() -> new RuntimeException("Item not found"));

                double totalPrice = orderDetailsDTO.getItemPrice() * orderDetailsDTO.getItemQuantity();

                OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
                OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orderDTO.getOrderId(), orderDetailsDTO.getItemId());
                orderDetailsEntity.setOrderDetailsPK(orderDetailsPK);
                orderDetailsEntity.setOrder(orderEntity);
                orderDetailsEntity.setItem(item);
                orderDetailsEntity.setItemQuantity(orderDetailsDTO.getItemQuantity());
                orderDetailsEntity.setItemPrice(orderDetailsDTO.getItemPrice());
                orderDetailsEntity.setTotalPrice(totalPrice);

                orderDetailsEntities.add(orderDetailsEntity);

                totalOrderPrice += totalPrice;
            }

            double discountAmount = 0.0;
            if (totalOrderPrice > 1000) {
                discountAmount = totalOrderPrice * 0.10;
            }

            double finalOrderPrice = totalOrderPrice - discountAmount;

            for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
                int itemDiscount = (int) ((discountAmount / totalOrderPrice) * orderDetailsEntity.getTotalPrice());
                double finalPriceAfterDiscount = orderDetailsEntity.getTotalPrice() - itemDiscount;

                orderDetailsEntity.setDiscount(itemDiscount);
                orderDetailsEntity.setTotalPrice(finalPriceAfterDiscount);
            }

            orderEntity.setOrderDetails(orderDetailsEntities);
            orderDao.save(orderEntity);

            System.out.println("Order saved: " + orderDTO.getOrderId() + ", Total Price: " + finalOrderPrice + ", Discount: " + discountAmount);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save order", e);
        }
    }
}