package com.merge.assignment.shoppingcart;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.merge.assignment.shoppingcart.model.User;
import com.merge.assignment.shoppingcart.model.UserDTO;
import com.merge.assignment.shoppingcart.service.CartService;
import com.merge.assignment.shoppingcart.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;



    @Test
    /**
     * TEsting the register user
     */
    public void testcase_1()throws Exception
    {
//        UserDTO user=new UserDTO();
//        user.setRole("USER");
//        user.setPassword("abc123");
//        user.setName("Test");
//        user.setUsername("test1");
//        user.setPhone("123456");
//        user.setEmail("test@edu");
//        Mockito.when(userService.save(user)).thenReturn(user.getUserFromDto());
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/users/register")
                        .content("{\"username\":\"test1\",\"email\":\"test@edu\",\"password\":\"abc123\",\"name\":\"Test\",\"phone\":\"123456\",\"role\":\"USER\"}")
                         .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString()
                            .contains("userId");
    }


    @Test
    /**
     * Testing the user login and generates token
     */
    public void testcase_2()throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")
                        .content("{\"username\":\"test1\",\"password\":\"abc123\"}")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("token");
   }



}
