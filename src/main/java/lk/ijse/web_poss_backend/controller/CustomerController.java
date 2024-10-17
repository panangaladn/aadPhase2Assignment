package lk.ijse.web_poss_backend.controller;

import lk.ijse.web_poss_backend.customObj.CustomerResponse;
import lk.ijse.web_poss_backend.dto.impl.CustomerDTO;
import lk.ijse.web_poss_backend.exception.CustomerNotFoundException;
import lk.ijse.web_poss_backend.exception.DataPersistFailedException;
import lk.ijse.web_poss_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Customer is running";
    }

    /**To Do CRUD Operation**/


    //Save Customer
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO){
        if (customerDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer cannot be null");
        }else {
            try {
                customerService.saveCustomer(customerDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    //Update Customer
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable("customerId") String customerId , @RequestBody CustomerDTO customer){
        try {
            if (customer == null && (customerId == null || customer.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.updateCustomer(customerId,customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Customer
    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("customerId") String customerId){
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Customer
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectCustomer(@PathVariable("customerId") String customerId){
        return customerService.getSelectCustomer(customerId);

    }

    //Get All Customers
    @GetMapping(value = "allCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
