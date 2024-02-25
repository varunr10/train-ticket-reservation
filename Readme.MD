Requirements:
I want to board a train from London to France. The train ticket will cost $5.

Create an API where you can submit a purchase for a ticket. Details included in the receipt are:
a) From, To, User , price paid.
(i) User should include first and last name, email address
The user is allocated a seat in the train. Assume the train has only 2 sections, section A and section B.
An API that shows the details of the receipt for the user
An API that lets you view the users and seat they are allocated by the requested section
An API to remove a user from the train
An API to modify a user's seat



Allowed APIS

1) Create Booking
```
URL: localhost:8081/booking/create
Method: POST
Request Body: 
{
"from":"LONDON",
"to":"PARIS",
"user": {
    "firstName":"Varun",
    "lastName":"Ram",
    "email":"VarunRam@gmail.com"
},
"pricePaid": 7.00
}
```
Response Body: 

{
    "receiptId": "2392bec6-5b72-4b6f-845d-1cb786324e44",
    "seatNumber": 1,
    "seatCategory": "LOWER",
    "section": "AC",
    "username": "Varun Ram"
}


2) View Details of teh receipt

Url: localhost:8081/booking/view/{receiptId)
Method: GET
Example url: localhost:8081/booking/view/2392bec6-5b72-4b6f-845d-1cb786324e44
Response body:
{
    "receiptId": "2392bec6-5b72-4b6f-845d-1cb786324e44",
    "seatNumber": 2,
    "seatCategory": "LOWER",
    "section": "AC",
    "username": "Varun Ram"
}


3) View users and seats by section

Url: localhost:8081/booking/all/{trainNumber}/section/{section}
Method: GET
Example Url: localhost:8081/booking/all/11111/section/AC
Response Body:
[
    {
        "seatNumber": 1,
        "userName": "Varun Ram"
    },
    {
        "seatNumber": 7,
        "userName": "Sachin Tendulkar"
    },
    {
        "seatNumber": 3,
        "userName": "Tiger Woods"
    },
    {
        "seatNumber": 4,
        "userName": "Valtenino Rossi"
    }
]


4) Remove user from a train

Url: localhost:8081/booking/delete/{trainNumber}/email/{emailId)
Method: Delete
Example Url: localhost:8081/booking/delete/11111/email/VarunRam@gmail.com
Response Body: Deleted ticket booking

5) Modify User seat 

Url: localhost:8081/booking/update/11111/email/{email}/newseat/{newseatnumber}
Method: PUT
Example Url: localhost:8081/booking/update/11111/email/VarunRam@gmail.com/newseat/7
Response Body: Modified Seat Number for user
