package com.the_dapda.domain.user.service;

import com.the_dapda.domain.user.entity.User;
import com.the_dapda.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 호출됨 - ID: " + id);  // 콘솔 출력 추가
        User user = userRepository.findById(id);
        if (user == null) {
            System.out.println("사용자 ID 찾을 수 없음 - ID: " + id);
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }




}
