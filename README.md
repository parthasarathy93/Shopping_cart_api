# Shopping_cart_api


# Frameworks
1. Java JDK 1.8+
2. Mysql server
3. Spring 
4. Hibernate



How to run the program?

1. mvn clean
2. mvn compile
3. mvn spring-boot:run
4. spin up the mysql container from the url 
  https://dev.to/celeron/spinning-up-mysql-database-with-docker-2d2a- I am using this docker container for spinning up mysql db
5. Run the initialization script under the folder database

How to run the unit test?
1. Initialize the database 
2. And then run the test

# API Doc:

## Register User:
Method: POST
https://localhost:8080/api/v1/users/register

Request Object:
     Note: all the fields are mandatory
Sample:
{
  "username": "user2",   
  "email": "user1@random.edu",
  "password": "abc123",
  "name": "John Doe",
  "phone": "1234567789",
  "role": "USER".   --> This field gives the role of the user , the values can be "USER","ADMIN"
}

Sample Response:
{
    "id": 3,
    "username": "user2",
    "email": "user1@random.edu",
    "phone": "1234567789",
    "name": "John Doe",
    "role": "USER",
    "roles": [
        {
            "id": 5,
            "name": "USER",
            "description": "User role"
        }
    ],
    "active": true
}


# Login User:
  ## Method:POST
  ### url: https://localhost:8080/api/v1/users/login
  Sample request:
      {
      "username": "user2",
      password:"abc123"
      }
  
  Sample response:
    {  "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjczNDcyMzUyLCJleHAiOjE2NzM0OTAzNTJ9.lRad3oDXMvQDpH4rPBjU6RcYqv2Q8FsoQ2JgbJFLgu4"
     }
     
 ### Note: After logging in you will use this access token for other CRUD operations, use it as header while performing api calls , the header key is "Authorization". and value is "Bearer <token>"
 
 
 Example:   <img width="631" alt="Screen Shot 2566-01-12 at 03 15 07" src="https://user-images.githubusercontent.com/14884964/211924526-86bd8866-6f80-4dfe-a300-e01aa75d9535.png">
 
 
 #USER ACTIONS:
 
 ## List Products:
  Method : GET
  
    https://localhost:8080/api/v1/products
    
    Sample Response:
    [
    {
        "description": "Product1",
        "price": 100,
        "stock": 10,
        "productId": 1
    },
    {
        "description": "Product2",
        "price": 50,
        "stock": 10,
        "productId": 2
    },
    {
        "description": "Product3",
        "price": 200,
        "stock": 50,
        "productId": 3
    }
]


## Add product to cart
  Method : POST

   https://localhost:8080/api/v1/carts
    
   Sample Request:
    {
       "productid": 1,
       "quantity": 10
     }
     
   Sample Response:
    {
    "userId": 3,
    "subtotal": 1000,
    "linesItems": [
        {
            "item_id": 1,
            "product": {
                "description": "Product1",
                "price": 100,
                "stock": 0,
                "productId": 1
            },
            "quantity": 10,
            "price": 100
        }
    ],
    "cartId": 1
}


## Update quantity of a product in cart
    
    Method : PUT
    
   https://localhost:8080/api/v1/carts
    
   Sample Request:
    {
       "productid": 1,
       "quantity": 10
     }

    Sample Response:
        {
        "userId": 3,
        "subtotal": 1000,
        "linesItems": [
            {
                "item_id": 1,
                "product": {
                    "description": "Product1",
                    "price": 100,
                    "stock": 0,
                    "productId": 1
                },
                "quantity": 10,
                "price": 100
            }
        ],
        "cartId": 1
    }
    
## Remove product from Cart
  
   Method : DELETE
    
   https://localhost:8080/api/v1/carts/{productId}
   
   ex: https://localhost:8080/api/v1/carts/1
    
    Sample response: After removing the product from cart
    {
    "userId": 3,
    "subtotal": 500,
    "linesItems": [
        {
            "item_id": 2,
            "product": {
                "description": "Product2",
                "price": 50,
                "stock": 0,
                "productId": 2
            },
            "quantity": 10,
            "price": 50
        }
    ],
    "cartId": 1
}
 
# Admin actions:

## Add product to system -- Note 
  
  Method: POST

  http://localhost:8080/api/v1/products

  Sample request body:
  {
    "description":"product190",
    "stock":25,
    "categoryid":1,
    "price":200

  }

  Sample response:

  {
      "description": "product190",
      "price": 200,
      "stock": 25,
      "productId": 4
  }


## Update stock of existing products:
  
  Method: PUT
  
  http://localhost:8080/api/v1/products

  Sample request body:

  {
    "description":"product190",
    "stock":25,
    "categoryid":1,
    "price":200

  }

  Sample response:

  {
      "description": "product190",
      "price": 200,
      "stock": 25,
      "productId": 4
  }

## Suspend a user

  Method: PUT

  http://localhost:8080/api/v1/users/suspend/<userid>

  We can get the <userid> using get request to 
  http://localhost:8080/api/v1/users/ 

  Sample response:

  {
      "id": 1,
      "username": "testuser",
      "email": "tes@edu",
      "phone": "12313",
      "name": "TestUser",
      "role": "USER",
      "roles": [],
      "active": false
  }








   
 
   







