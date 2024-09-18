package com.the_dapda.domain.user.repository;

import com.the_dapda.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);
    User findByNickname(String nickname);
}
