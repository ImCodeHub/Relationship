package com.DTO.Relationship.Controller;

import com.DTO.Relationship.Entity.Comment;
import com.DTO.Relationship.Entity.Student;
import com.DTO.Relationship.Entity.User;
import com.DTO.Relationship.Model.CommentModel;
import com.DTO.Relationship.Model.PostModel;
import com.DTO.Relationship.Model.StudentModel;
import com.DTO.Relationship.Model.UserModel;
import com.DTO.Relationship.Service.ServiceImplementation.CommentService;
import com.DTO.Relationship.Service.ServiceImplementation.PostServiceImpl;
import com.DTO.Relationship.Service.ServiceImplementation.StudentService;
import com.DTO.Relationship.Service.ServiceImplementation.UserServiceImpl;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("user/api")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private StudentService studentService;

    @PostMapping("save-user")
    public ResponseEntity<String> saveUserDetails(@Valid @RequestPart("user") UserModel userModel, @RequestParam("file")MultipartFile file) throws IOException {
        String response = userServiceImpl.saveUser(userModel, file);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserModel>> getAllUserDetails(){
        List<UserModel> userList = userServiceImpl.getUserList();
        return new ResponseEntity<>(userList,HttpStatus.FOUND);
    }

    @GetMapping("get-user/by/{id}")
    public ResponseEntity<UserModel> getUserDetailsById(@PathVariable Integer id) throws IOException {
        UserModel userDetails = userServiceImpl.findUserDetails(id);
        return new ResponseEntity<>(userDetails, HttpStatus.FOUND);
    }

    @PostMapping("post")
    public ResponseEntity<String> creatPost(@RequestBody PostModel postModel){
        String response = postService.createPostInDb(postModel);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("comment/{postId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Integer postId,
            @RequestBody CommentModel commentModel
    ){
        Comment commentOnPost = commentService.createCommentOnPost(postId, commentModel);
        return new ResponseEntity<>(commentOnPost, HttpStatus.CREATED);

    }

    @PostMapping("register")
    public ResponseEntity<Student> studentRegistration(@RequestBody StudentModel studentModel){
        Student student = studentService.registerStudent(studentModel);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}
