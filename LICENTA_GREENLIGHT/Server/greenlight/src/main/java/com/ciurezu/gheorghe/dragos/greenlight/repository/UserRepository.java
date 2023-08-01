package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findAllByOrderByScoreDesc();
    @Modifying
    @Transactional
    @Query("update User u set u.coins = u.coins+?1, u.score = u.score+ ?2 where u.username = ?3")
    int updateCoins(Long coins, Long points, String username);
}