package lk.ijse.web_poss_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity{
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobile;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();
}

