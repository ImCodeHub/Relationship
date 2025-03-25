package com.DTO.Relationship.Repository;

import com.DTO.Relationship.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.id IN :ids")
    Set<Course> findByIds(@Param("ids") Set<Integer> ids);
}
