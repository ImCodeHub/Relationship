package com.DTO.Relationship.Service.ServiceImplementation;

import com.DTO.Relationship.Entity.Profile;
import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.UserModel;
import com.DTO.Relationship.Repository.ProfileRepository;
import com.DTO.Relationship.Repository.UserRepository;
import com.DTO.Relationship.Service.ServiceInterface.UserServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public String saveUser(UserModel userModel) {
        // Create Profile entity
        Profile profile = new Profile();
        profile.setAddress(userModel.getAddress());
        profile.setCity(userModel.getCity());
        profile.setState(userModel.getState());
        profile.setMobileNumber(userModel.getMobileNumber());
        profile.setDob(userModel.getDob());

        // Create User entity
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());

        user.setProfile(profile); // Associate profile

        userRepository.save(user);
        return "User Details has saved in Database.";
    }

    @Override
    public List<UserModel> getUserList() {
//        List<UserModel> userModelList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
//        for(User user : userList){
//            UserModel userModel = new UserModel();
//            userModel.setFirstName(user.getFirstName());
//            userModel.setLastName(user.getLastName());
//            userModel.setEmail(user.getEmail());
//            userModel.setAddress(user.getProfile().getAddress());
//            userModel.setCity(user.getProfile().getCity());
//            userModel.setState(user.getProfile().getState());
//            userModel.setDob(user.getProfile().getDob());
//
//            userModelList.add(userModel);
//
//        }

        return userList.stream().map(user -> {
            UserModel userModel = new UserModel();
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setEmail(user.getEmail());
            userModel.setAddress(user.getProfile().getAddress());
            userModel.setCity(user.getProfile().getCity());
            userModel.setState(user.getProfile().getState());
            userModel.setDob(user.getProfile().getDob());
            return userModel;
        }).collect(Collectors.toList());

    }

    @Override
    public UserModel findUserDetails(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        UserModel userModel = new UserModel();
        userModel.setFirstName(user.getFirstName()+ " "+ user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setCity(user.getProfile().getCity());
        userModel.setMobileNumber(user.getProfile().getMobileNumber());

        return userModel;

    }


}
