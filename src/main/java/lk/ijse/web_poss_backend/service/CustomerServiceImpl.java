package lk.ijse.web_poss_backend.service;

import lk.ijse.web_poss_backend.customObj.CustomerErrorResponse;
import lk.ijse.web_poss_backend.customObj.CustomerResponse;
import lk.ijse.web_poss_backend.dao.CustomerDao;
import lk.ijse.web_poss_backend.dto.impl.CustomerDTO;
import lk.ijse.web_poss_backend.entity.CustomerEntity;
import lk.ijse.web_poss_backend.exception.CustomerNotFoundException;
import lk.ijse.web_poss_backend.exception.DataPersistFailedException;
import lk.ijse.web_poss_backend.util.AppUtil;
import lk.ijse.web_poss_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapping mapping;

    //Save Customer
    public void saveCustomer(CustomerDTO customerDTO) {
        List<String> customerIds = customerDao.findLastCustomerId();
        String lastCustomerId = customerIds.isEmpty() ? null : customerIds.get(0);
        customerDTO.setCustomerId(AppUtil.generateNextCustomerId(lastCustomerId));

        var customerEntity = mapping.convertToCustomerEntity(customerDTO);
        var saveCustomer = customerDao.save(customerEntity);
        if (saveCustomer == null) {
            throw new DataPersistFailedException("Can't save customer");
        }
    }

    //Update Customer
    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> tmpNoteEntity = customerDao.findById(customerId);
        if(!tmpNoteEntity.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpNoteEntity.get().setCustomerName(customerDTO.getCustomerName());
            tmpNoteEntity.get().setCustomerAddress(customerDTO.getCustomerAddress());
            tmpNoteEntity.get().setCustomerMobile(customerDTO.getCustomerMobile());
        }
    }

    //Delete Customer
    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> findId = customerDao.findById(customerId);
        if(!findId.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }else {
            customerDao.deleteById(customerId);
        }
    }

    //Get Customer
    @Override
    public CustomerResponse getSelectCustomer(String customerId) {
        if (customerDao.existsById(customerId)) {
            return mapping.convertToCustomerDTO(customerDao.getReferenceById(customerId));
        }else {
            return new CustomerErrorResponse(0,"Customer not found");
        }
    }

    //Get All Customers
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertCustomerToDTO(customerDao.findAll());
    }
}