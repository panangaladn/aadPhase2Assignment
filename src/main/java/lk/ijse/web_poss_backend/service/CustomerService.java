package lk.ijse.web_poss_backend.service;

import lk.ijse.web_poss_backend.customObj.CustomerResponse;
import lk.ijse.web_poss_backend.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(String customerId, CustomerDTO customerDTO);

    void deleteCustomer(String customerId);

    CustomerResponse getSelectCustomer(String customerId);

    List<CustomerDTO> getAllCustomers();

}
