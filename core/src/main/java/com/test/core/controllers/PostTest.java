//package com.test.core.controllers;
//
//import com.test.core.Users;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PostTest {
//    @RequestMapping(value = "/test/postmethod/datausers", method = RequestMethod.POST, consumes ="application/json")
//    public String getData(@RequestBody Users users){
//        return "User id: " + users.getId()+"\nusername: " + users.getUsername()+ "\nemail: " + users.getEmail();
//    }
//}
