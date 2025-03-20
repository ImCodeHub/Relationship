package com.DTO.Relationship.Service.ServiceInterface;

import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.UserModel;

import java.util.List;

public interface UserServiceInterface {
    public String saveUser(UserModel userModel);
    public List<UserModel> getUserList();
}
