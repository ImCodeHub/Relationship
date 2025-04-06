package com.DTO.Relationship.Repository;

import com.DTO.Relationship.Entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {

}
