package com.DTO.Relationship.Service.ServiceImplementation;

import com.DTO.Relationship.Entity.Course;
import com.DTO.Relationship.Entity.Student;
import com.DTO.Relationship.Model.StudentModel;
import com.DTO.Relationship.Repository.CourseRepository;
import com.DTO.Relationship.Repository.StudentRepository;
import com.DTO.Relationship.Service.ServiceInterface.StudentInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService implements StudentInterface {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student registerStudent(StudentModel studentModel) {
        Set<Course> courseSet = courseRepository.findByIds(studentModel.getCoursesId());
        Student student = Student.builder()
                .firstName(studentModel.getFirstName())
                .lastName(studentModel.getLastName())
                .email(studentModel.getEmail())
                .courses(courseSet).build();

        for (Course course : courseSet) {
            course.getStudents().add(student);
        }
        return studentRepository.save(student);
    }
}
