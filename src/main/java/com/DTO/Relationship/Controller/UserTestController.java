package com.DTO.Relationship.Controller;

import com.DTO.Relationship.Entity.UserTest;
import com.DTO.Relationship.Service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user-testing")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @PostMapping("register")
    public ResponseEntity<String> userRegistration(@RequestBody UserTest userTest){
        String response = userTestService.saveUser(userTest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-user/{id}")
    public ResponseEntity<UserTest> getUserByIdFromDb(@PathVariable Long id){
        UserTest user = userTestService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
