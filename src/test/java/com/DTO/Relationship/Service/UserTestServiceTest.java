package com.DTO.Relationship.Service;

import com.DTO.Relationship.Entity.UserTest;
import com.DTO.Relationship.Repository.UserTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // it is good practice to add this otherwise it will not test the unit.
public class UserTestServiceTest {

    @Mock
    private UserTestRepository userTestRepository;

    @InjectMocks
    private UserTestService userTestService;

    UserTest userTest;

    @BeforeEach
    void setUp(){
        /** MockitoAnnotations.openMocks(this);// either you can add here in the setUp or you can use on top of the class you created for test.*/
        userTest = UserTest.builder()
                .id(1L)
                .name("Shaktiman")
                .age(25)
                .email("Gangadhar@gmail.com").build();
    }

    @AfterEach
    void tearDown(){
        userTest=null;
    }

    @Test
    public void TestSaveUser(){
        //mock userTestRepository and pretend that when you save any userTest just return userTest.
        when(userTestRepository.save(any(UserTest.class))).thenReturn(userTest);
        //get the exact response(result) from service.
        String response = userTestService.saveUser(userTest);
        //and match that result is matching with expected result or not?
        assertThat(response).isEqualTo("user has successfully saved in db.");
    }

    @Test
    public void testGetUserById(){
        Long userId = 1L;
        when(userTestRepository.findById(userId)).thenReturn(Optional.ofNullable(userTest));
        UserTest user = userTestService.getUserById(userId);
        assertThat(user.getId()).isEqualTo(userTest.getId());
        assertThat(user.getName()).isEqualTo((userTest.getName()));
        assertThat(user.getEmail()).isEqualTo(userTest.getEmail());
        assertThat(user.getAge()).isEqualTo(userTest.getAge());
    }


}
