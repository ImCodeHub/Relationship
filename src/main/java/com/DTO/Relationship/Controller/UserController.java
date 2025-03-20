package com.DTO.Relationship.Controller;

import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.UserModel;
import com.DTO.Relationship.Service.ServiceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/api")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("save-user")
    public ResponseEntity<String> saveUserDetails(@RequestBody UserModel userModel){
        String response = userServiceImpl.saveUser(userModel);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserModel>> getAllUserDetails(){
        List<UserModel> userList = userServiceImpl.getUserList();
        return new ResponseEntity<>(userList,HttpStatus.FOUND);
    }

}
