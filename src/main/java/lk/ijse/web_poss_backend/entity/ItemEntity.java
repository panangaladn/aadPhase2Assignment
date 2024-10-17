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
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemId;
    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String itemCategory;
    @Column(columnDefinition = "LONGTEXT")
    private String itemImage;


    // OneToMany relationship with OrderDetailsEntity
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();

}