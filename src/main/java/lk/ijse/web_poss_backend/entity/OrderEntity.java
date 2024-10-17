package lk.ijse.web_poss_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @CreationTimestamp
    private Timestamp orderTimestamp;



    // OneToMany relationship with OrderDetailsEntity
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();
}















//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "order_details",
//            joinColumns = @JoinColumn(name = "orderId"),
//            inverseJoinColumns = @JoinColumn(name = "itemId")
//    )
//    private List<ItemEntity> items = new ArrayList<>();