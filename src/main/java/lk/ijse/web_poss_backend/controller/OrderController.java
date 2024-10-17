package lk.ijse.web_poss_backend.controller;

import lk.ijse.web_poss_backend.dto.impl.OrderDTO;
import lk.ijse.web_poss_backend.exception.DataPersistFailedException;
import lk.ijse.web_poss_backend.exception.OrderNotFoundException;
import lk.ijse.web_poss_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Order is running";
    }


    // Place Order
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> placeOrder(@RequestBody OrderDTO orderDTO) {
       if (orderDTO == null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order cannot be null");
       }else {
           try {
               orderService.saveOrder(orderDTO);
               return new ResponseEntity<>(HttpStatus.CREATED);
           }catch (DataPersistFailedException e) {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }catch (Exception e) {
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
    }
}