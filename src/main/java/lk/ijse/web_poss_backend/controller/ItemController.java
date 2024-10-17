package lk.ijse.web_poss_backend.controller;


import lk.ijse.web_poss_backend.customObj.ItemResponse;
import lk.ijse.web_poss_backend.dto.impl.ItemDTO;
import lk.ijse.web_poss_backend.exception.DataPersistFailedException;
import lk.ijse.web_poss_backend.exception.ItemNotFoundException;
import lk.ijse.web_poss_backend.service.ItemService;
import lk.ijse.web_poss_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Item is running";
    }

    //Save Item
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sveItem(
            @RequestPart("itemName") String itemName,
            @RequestPart("itemPrice") String itemPrice,
            @RequestPart("itemQuantity") String itemQuantity,
            @RequestPart("itemCategory") String itemCategory,
            @RequestPart("itemImage") MultipartFile itemImage) {

        try {
            byte[] imageByteCollection = itemImage.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection);
            ItemDTO buildItemDTO = new ItemDTO();
            buildItemDTO.setItemName(itemName);
            buildItemDTO.setItemPrice(Integer.parseInt(itemPrice));
            buildItemDTO.setItemQuantity(Integer.parseInt(itemQuantity));
            buildItemDTO.setItemCategory(itemCategory);
            buildItemDTO.setItemImage(base64ProfilePic);

            // Send to the service layer
            itemService.saveItem(buildItemDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Error occurred while saving item: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Item
    @PatchMapping(value = "/{itemId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateItem(
            @PathVariable("itemId") String itemId,
            @RequestPart("updateItemName") String updateItemName,
            @RequestPart("updateItemPrice") String updateItemPrice,
            @RequestPart("updateItemQuantity") String updateItemQuantity,
            @RequestPart("updateItemCategory") String updateItemCategory,
            @RequestPart("updateItemImage") MultipartFile updateItemImage) {
        try {
            // Get the image bytes and convert to Base64
            byte[] imageBytes = updateItemImage.getBytes();
            String base64ItemPic = AppUtil.toBase64ProfilePic(imageBytes);

            // Create the DTO for the updated item
            ItemDTO updateItem = new ItemDTO();
            updateItem.setItemId(itemId);
            updateItem.setItemName(updateItemName);
            updateItem.setItemPrice(Integer.parseInt(updateItemPrice));
            updateItem.setItemQuantity(Integer.parseInt(updateItemQuantity));
            updateItem.setItemCategory(updateItemCategory);
            updateItem.setItemImage(base64ItemPic);

            // Call the service to update the item
            itemService.updateItem(updateItem);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error occurred while updating item: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String itemId) {
        try {
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Item
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getSelectedItem(@PathVariable ("id") String itemId){
        return itemService.getSelectItem(itemId);
    }

    //Get All Item
    @GetMapping(value = "allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }
}