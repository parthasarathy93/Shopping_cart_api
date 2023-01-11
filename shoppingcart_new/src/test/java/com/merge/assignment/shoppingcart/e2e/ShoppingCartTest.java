package com.merge.assignment.shoppingcart.e2e;


import java.util.List;
import java.util.Map;

import com.merge.assignment.shoppingcart.ShoppingcartApplication;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ShoppingcartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartTest {

    public static void main(String[] args)throws Exception {
        String baseUrl = "http://localhost:8080";
        SpringApplication.run(ShoppingcartApplication.class, args);
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        //Step - 1
        //Test will start from Registering a User with role as "USER"
        request.header("Content-Type", "application/json");
        Response response = request.body("{\"username\":\"admin\",\"email\":\"user1@random.edu\",\"password\":\"admin123\",\"name\":\"John Doe\",\"phone\":\"1234567789\",\"role\":\"ADMIN\"}")
                .post("/api/v1/users/register");
        Assert.assertEquals(response.getStatusCode(), 200);
        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("id"));
        //Step -2
        //Generate Token
         response = request.body("{\"username\":\"admin\",\"password\":\"admin123\"}")
                .post("/api/v1/users/login");
        Assert.assertEquals(response.getStatusCode(), 200);
         jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("token"));
        //This token will be used in later requests
        String token = JsonPath.from(jsonString).get("token");


        //Step-3
       //List available items
        request.header("Authorization","Bearer "+token);
        response = request.get("/api/v1/products");
        Assert.assertEquals(response.getStatusCode(), 200);jsonString = response.asString();
        List<Map<String, String>> products = JsonPath.from(jsonString).get();
        Assert.assertTrue(products.size() > 0);



        //This token will be used in later requests


    }
}