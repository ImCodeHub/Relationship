package com.DTO.Relationship.Model;

import com.DTO.Relationship.Entity.Course;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private String firstName;
    private String lastName;
    private String email;
    private Set<Integer> coursesId; //Ids of the course which student wants to enroll.[1,3]
}
