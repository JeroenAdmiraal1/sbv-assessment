# Lease-a-car API

This API allows you to lease a car to a customer.

Via its endpoints you can maintain car versions, customer information, and calculate the lease rate for a customer.

## Install and run the program

Clone the repository from gitlab and run it in your IDE.

Use Postman or a similar program to send information to this API. Use of mediatype JSON is recommended.

## API Endpoints

For accessing the car endpoints, the following are available:

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | /car | To create a new car |
| PATCH | /car/{id} | To patch an existing car |
| DELETE | /car/{id} | To delete an existing car |
| POST | /car/upload | To upload a CSV file of cars |

For accessing the customer endpoints, the following are available:

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | /customer | To create a new customer |
| PATCH | /customer/{id} | To patch an existing customer |
| DELETE | /customer/{id} | To delete an existing customer |

For accessing the leaserate endpoints, the following is available:

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| GET | /lease/mileage/{mileage}/duration/{duration}/date/{startDate}/car/{carId} | To get the lease rate for a car |
