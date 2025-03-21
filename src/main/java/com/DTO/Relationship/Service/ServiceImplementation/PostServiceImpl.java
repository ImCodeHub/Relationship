package com.DTO.Relationship.Service.ServiceImplementation;

import com.DTO.Relationship.Entity.Post;
import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.PostModel;
import com.DTO.Relationship.Repository.PostRepository;
import com.DTO.Relationship.Repository.UserRepository;
import com.DTO.Relationship.Service.ServiceInterface.PostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostInterface {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createPostInDb(PostModel postModel) {
        User user = userRepository.findById(postModel.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
//        Post post = new Post();
//        post.setTitle(postModel.getTitle());
//        post.setDescription(postModel.getDescription());
//        post.setUser(user);

//        by using Builder from Lombok
        Post post = Post.builder()
                .title(postModel.getTitle())
                .description(postModel.getDescription())
                .user(user)
                .build();
        postRepository.save(post);
        return "Posted Successfully!";
    }
}
