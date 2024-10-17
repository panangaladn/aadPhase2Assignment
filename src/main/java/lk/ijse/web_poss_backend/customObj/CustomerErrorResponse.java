package lk.ijse.web_poss_backend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerErrorResponse implements CustomerResponse,Serializable {
    private int errorCode;
    public String errorMessage;
}
