package com.DTO.Relationship.Service.ServiceInterface;

import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.UserModel;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserServiceInterface {
    public String saveUser(UserModel userModel, MultipartFile imageFile) throws IOException, MessagingException;
    public List<UserModel> getUserList();
    public UserModel findUserDetails(Integer userId) throws IOException;
}
