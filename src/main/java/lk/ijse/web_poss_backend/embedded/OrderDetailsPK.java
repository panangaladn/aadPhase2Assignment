package lk.ijse.web_poss_backend.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailsPK implements Serializable {

    @Column(name = "orderId")
    private String orderId;
    @Column(name = "itemId")
    private String itemId;
}
