# Web POSS Backend System

This is a web-based Point of Sale (POS) backend system built using Spring Framework and Hibernate. The system provides functionalities for managing customers, items, and orders, and supports image handling via Base64 encoding for item management.

## Technologies Used
- *Spring Framework* (for RESTful API development)
- *Hibernate* (for ORM and database interactions)
- *Maven* (for project dependency management)
- *MySQL* (or any preferred database)

## API Endpoints

### Customer API
1. *Create a New Customer*  
   *POST:* http://localhost:8080/web_poss_backend/api/v1/customers  
   *Example Request Body:*
   json
   {
   "customerName": "Saman",
   "customerAddress": "Kaluthara",
   "customerMobile": "0714498545"
   }


2. **Update an Existing Customer**  
   **PUT:** `http://localhost:8080/web_poss_backend/api/v1/customers/CUS-002`  
   **Example Request Body:**
   json
   {
   "customerName": "Kumara",
   "customerAddress": "Galla",
   "customerMobile": "075747544"
   }

3. *Get Customer by ID*  
   *GET:* http://localhost:8080/web_poss_backend/api/v1/customers/CUS-004

4. *Get All Customers*  
   *GET:* http://localhost:8080/web_poss_backend/api/v1/customers/allCustomers

5. *Delete Customer by ID*  
   *DELETE:* http://localhost:8080/web_poss_backend/api/v1/customers/{customerId}



### Item API
1. *Create a New Item*  
   *POST:* http://localhost:8080/web_poss_backend/api/v1/items  
   *Example Request Body (Form Data):*
   json
   {
   "itemName": "Anchor",
   "itemPrice": 1050,
   "itemQuantity": 100,
   "itemImage": "<Base64 Encoded Image String>File"
   }

2. **Update an Existing Item**  
   **PUT:** `http://localhost:8080/web_poss_backend/api/v1/items/ITE-002`  
   **Example Request Body (Form Data):**
   json
   {
   "itemName": "Anchor",
   "itemPrice": 1200,
   "itemQuantity": 100,
   "itemImage": "<Base64 Encoded Image String>File"
   }

3. *Get Item by ID*  
   *GET:* http://localhost:8080/web_poss_backend/api/v1/items/ITE-003
4. *Get All Items*  
   *GET:* http://localhost:8080/web_poss_backend/api/v1/items
5. *Delete Item by ID*  
   *DELETE:* http://localhost:8080/web_poss_backend/api/v1/items/{itemId}

### Order API
1. *Create a New Order*  
   *POST:* http://localhost:8080/web_poss_backend/api/v1/orders  
   *Example Request Body:*
   json
   {
   "customerId": "CUS-001",
   "orderDetails": [
   {
   "itemId": "ITE-001",
   "itemQuantity": 2,
   "itemPrice": 1250,
   },
   {
   "itemId": "ITE-002",
   "itemQuantity": 2,
   "itemPrice": 1500,
   },
   ]
   }

## Running the Application on Apache Tomcat

To run the web-based POS backend system on an Apache Tomcat server, follow these steps:

### Prerequisites
- Apache Tomcat installed and running on your machine (version 9+ recommended).
- Java 8 or higher installed.

## Testing with Postman
**Steps to Test:**
- Open Postman and create a new Request.
- Choose the appropriate HTTP method (GET, POST, PUT, DELETE).
- Enter the API URL (e.g., http://localhost:8080/web_poss_backend/api/v1/customers).
- For POST/PUT requests, set the Body to raw and select JSON (for customer and order endpoints) or Form Data (for item endpoints).
- Add the required JSON or form data in the body.
- Click Send to execute the request and view the response.

### Steps to Run the Application:

1. **Clone the Repository**
   bash
   git clone https://github.com/panangaladn/aadPhase2Assignment.git