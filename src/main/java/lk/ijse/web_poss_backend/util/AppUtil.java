package lk.ijse.web_poss_backend.util;

import lk.ijse.web_poss_backend.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class AppUtil {

    private static ItemService itemService;


    //Customer ID Generate
    public static String generateNextCustomerId(String lastCustomerId) {
        if (lastCustomerId != null && lastCustomerId.startsWith("CUS-")) {
            int lastIdNumber = Integer.parseInt(lastCustomerId.substring(4));
            return String.format("CUS-%03d", lastIdNumber + 1);
        }
        return "CUS-001";
    }

    //Item ID Generate
    public static String generateNextItemId(String lastItemId) {
        if (lastItemId != null && lastItemId.startsWith("ITE-")) {
            int lastIdNumber = Integer.parseInt(lastItemId.substring(4));
            return String.format("ITE-%03d", lastIdNumber + 1);
        }
        return "ITE-001";
    }

    //Order ID Generate
    public static String generateNextOrderId(String lastOrderId) {
        if (lastOrderId != null && lastOrderId.startsWith("ORD-")) {
            // Extract the numeric part after "ORD-"
            int lastIdNumber = Integer.parseInt(lastOrderId.substring(4)); // Get the number part
            return String.format("ORD-%03d", lastIdNumber + 1); // Increment and format
        }
        return "ORD-001"; // Default if no last order ID is present
    }


    public static String toBase64ProfilePic(byte[] profilePic) {
        // Convert image bytes to Base64 string
        return Base64.getEncoder().encodeToString(profilePic);
    }

    //public static String toBase64ProfilePic(String profilePic){
    //     //used to java Base64 class, and profilePic convert Base64 encode
//        return Base64.getEncoder().encodeToString(profilePic.getBytes());
//    }
}
