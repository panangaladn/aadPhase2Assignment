package lk.ijse.web_poss_backend.dto.impl;


import lk.ijse.web_poss_backend.customObj.CustomerResponse;
import lk.ijse.web_poss_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements SuperDTO, CustomerResponse{
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobile;
}
