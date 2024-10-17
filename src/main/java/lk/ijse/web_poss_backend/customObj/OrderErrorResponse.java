package lk.ijse.web_poss_backend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderErrorResponse implements Serializable,OrderResponse {
    private int errorCode;
    public String errorMessage;
}
