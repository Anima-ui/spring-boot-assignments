# STARTING THE APP

To run the app write in the console: 

```bash
./mvnw spring-boot:run
```

## API DOCS
To access API documentation open in your browser: `localhost:8080/swagger-ui-docs`
Then search for a docs using: `/api-docs/discount`

### REQUIREMENTS
Spring Boot application with the below requirements was implemented:

-Creation of Customers.
-Customers can create orders.
-For simplicity, once an order is created, that's final. There is no order status like created, payment done, completed, etc. It is assumed that the customer has already made the payment while creating the order.
-Customers are categorized as regular, gold, platinum.
-By default, a customer is regular.
-Customer is promoted to gold when they have placed 10 orders.
-Customer is promoted to platinum when they have placed 20 orders.
-Gold = 10% discount, Platinum = 20% discount.
-When a customer creates an order, if they are a gold customer, automatically 10% discount is applied to the order. 20% for platinum customers.
-Since it is assumed that the customer has already made the full payment during creation of the order, this discount information has to be kept safe by the application.
-We need to keep track of how much discount is given to which customer and for which order, so that customers can claim money back later.
-It is not mandatory to implement any other entities which are not mentioned here, like products or payments, etc.