package com.the_dapda.domain.user.service;

import com.the_dapda.domain.code.entity.GroupCode;
import com.the_dapda.domain.code.repository.GroupCodeRepository;
import com.the_dapda.domain.user.dto.LoginDto;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.entity.Role;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); // 로그 추가

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final GroupCodeRepository groupCodeRepository;

    @Override
    @Transactional
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
        user.setRoleCode(Role.ROLE_NORMAL);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNickname(userDto.getNickname());

        GroupCode groupCode = GroupCode.builder()
                .groupCode("001")
                .groupCodeName(String.valueOf(Role.ROLE_NORMAL))
                .groupCodeDesc("가입하는 회원을 구분한다.")
                .build();

        groupCodeRepository.save(groupCode);
        return userRepository.save(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        System.out.println("login 메서드 호출됨 - ID: " + loginDto.getId()); // 콘솔 출력 추가

        // 사용자의 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword())
        );

        System.out.println("인증 성공 - ID: " + loginDto.getId());

        // 세션에 사용자 정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 사용자 정보 조회 후 반환
        User user = userRepository.findById(loginDto.getId());
        System.out.println("DB에서 사용자 조회 성공 - ID: " + loginDto.getId());
        return user;
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
