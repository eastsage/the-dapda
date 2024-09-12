package com.the_dapda.domain.user.repository;



import com.the_dapda.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 예제 13.7
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);
    Optional<User> findByNickname(String nickname);

}