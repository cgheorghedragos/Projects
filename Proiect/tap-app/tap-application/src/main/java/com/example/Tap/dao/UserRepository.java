package com.example.Tap.dao;

import com.example.Tap.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query(value = "SELECT u FROM users u WHERE u.username LIKE %:username%")
    List<UserEntity> findUserEntitiesByUsernameContains(String username);
}
