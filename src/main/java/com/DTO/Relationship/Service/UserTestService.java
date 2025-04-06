package com.DTO.Relationship.Service;

import com.DTO.Relationship.Entity.UserTest;
import com.DTO.Relationship.Repository.UserTestRepository;
import com.DTO.Relationship.Service.Utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserTestRepository userTestRepository;
    public String saveUser(UserTest userTest) {
        UserTest user = userTestRepository.save(userTest);
        return "user has successfully saved in db.";
    }

    public UserTest getUserById(Long id){
        UserTest user = userTestRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found by this id: " + id));
        return user;
    }
}
