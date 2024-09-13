package com.the_dapda.domain.user.service;

import com.the_dapda.domain.user.dto.LoginDto;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserDto userDto) {
        // 중복된 ID 체크
        if (userRepository.findById(userDto.getId()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
        }
        // 중복된 닉네임 체크
        if (userRepository.findByNickname(userDto.getNickname()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 새로운 User 객체 생성 및 저장
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNickname(userDto.getNickname());
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        // 사용자의 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword())
        );

        // 세션에 사용자 정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 사용자 정보 조회 후 반환
        return userRepository.findById(loginDto.getId());
    }

    @Override
    public void logout() {
        // 세션 초기화 (로그아웃)
        SecurityContextHolder.clearContext();
    }

    @Override
    public User findUserById(String userId) {
        // 사용자 정보 조회
        return userRepository.findById(userId);
    }
}
