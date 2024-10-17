package lk.ijse.web_poss_backend.util;

import lk.ijse.web_poss_backend.dto.impl.CustomerDTO;
import lk.ijse.web_poss_backend.dto.impl.ItemDTO;
import lk.ijse.web_poss_backend.dto.impl.OrderDTO;
import lk.ijse.web_poss_backend.dto.impl.OrderDetailsDTO;
import lk.ijse.web_poss_backend.entity.CustomerEntity;
import lk.ijse.web_poss_backend.entity.ItemEntity;
import lk.ijse.web_poss_backend.entity.OrderDetailsEntity;
import lk.ijse.web_poss_backend.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;


    /**CustomerEntity to Convert CustomerDTO  and CustomerDTO to Convert CustomerEntity **/


    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public CustomerDTO convertToCustomerDTO(CustomerEntity customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> convertCustomerToDTO(List<CustomerEntity> customerEntities) {
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>() {}.getType());
    }


    //----------------------------Item---------------------------

    public ItemEntity convertToItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public ItemDTO convertToItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }


    public List<ItemDTO> convertItemToDTOList(List<ItemEntity> itemEntities) {
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDTO>>() {}.getType());
    }


    //----------------------------Order---------------------------

    public OrderEntity convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }

    public OrderDTO convertToOrderDTO(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDTO.class);
    }


    public List<OrderDTO> convertOrderDTOToList(List<OrderEntity> orderEntities) {
        return modelMapper.map(orderEntities, new TypeToken<List<OrderDTO>>() {}.getType());
    }


    //----------------------------Order-Details--------------------------

    public OrderDetailsEntity convertToEntity(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetailsEntity.class);
    }

    public OrderDetailsDTO convertToOrderDetailsDTO(OrderDetailsEntity orderDetailsEntity) {
        return modelMapper.map(orderDetailsEntity, OrderDetailsDTO.class);
    }


    public List<OrderDetailsDTO> convertOrderDetailsDTOToList(List<OrderDetailsEntity> orderDetailsEntities) {
        return modelMapper.map(orderDetailsEntities, new TypeToken<List<OrderDetailsDTO>>() {}.getType());
    }

}
