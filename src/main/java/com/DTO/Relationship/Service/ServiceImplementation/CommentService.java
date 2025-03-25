package com.DTO.Relationship.Service.ServiceImplementation;

import com.DTO.Relationship.Entity.Comment;
import com.DTO.Relationship.Entity.Post;
import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.CommentModel;
import com.DTO.Relationship.Repository.CommentRepository;
import com.DTO.Relationship.Repository.PostRepository;
import com.DTO.Relationship.Repository.UserRepository;
import com.DTO.Relationship.Service.ServiceInterface.CommentInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentInterface {
    private final static Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createCommentOnPost(Integer postId, CommentModel commentModel) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found by this Id: " + postId));
//        logger.error("post details: "+ post);
        User user = userRepository.findById(commentModel.getUserId()).orElseThrow(() -> new RuntimeException("User not found by this Id: " + commentModel.getUserId()));
//        logger.error("user details: "+ user);

        Comment comment = Comment.builder()
                .comment(commentModel.getComment())
                .post(post)
                .user(user)
                .build();
        commentRepository.save(comment);
        return comment;
    }
}
