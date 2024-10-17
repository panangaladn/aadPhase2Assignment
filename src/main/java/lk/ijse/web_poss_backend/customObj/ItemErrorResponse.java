package lk.ijse.web_poss_backend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemErrorResponse implements Serializable,ItemResponse {
    private int errorCode;
    public String errorMessage;
}
