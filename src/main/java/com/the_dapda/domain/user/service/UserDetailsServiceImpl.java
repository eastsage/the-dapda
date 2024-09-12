package com.the_dapda.domain.user.service;


import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.domain.user.repository.UserRepository;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import com.the_dapda.global.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


// 예제 13.8
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    public ResponseForm register(UserDto userDto) {
        // ID 중복 확인
        if (userRepository.findById(userDto.getId()).isPresent()) {
            return ResponseForm.of(ResponseCode.AUTH_REGISTER_DUPLICATE_ID, "이미 사용 중인 ID입니다.");
        }
        if(userRepository.findByNickname(userDto.getNickname()).isPresent()){
            return ResponseForm.of(ResponseCode.AUTH_REGISTER_DUPLICATE_NICKNAME, "이미 사웅 중인 닉네임입니다.");
        }


        // 비밀번호 암호화 및 회원 저장
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setId(userDto.getId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNickname(userDto.getNickname()); // 닉네임을 이름으로 사용

        userRepository.save(user);
        return ResponseForm.of(ResponseCode.AUTH_REGISTER_SUCCESS, "회원가입 성공");
    }


    public ResponseForm login(UserDto memberDto) {
        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(memberDto.getId(), memberDto.getPassword()));

            // 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 인증된 사용자 정보에서 권한 추출 (List<String>)
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // JWT 토큰 발급 (사용자 ID와 권한 전달)
            String token = jwtProvider.createToken(memberDto.getId(), roles);
            return ResponseForm.of(ResponseCode.AUTH_LOGIN_SUCCESS, token);
        } catch (Exception e) {
            return ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, "로그인 실패");
        }
    }

    public ResponseForm logout() {
        // 로그아웃은 보통 클라이언트 측에서 JWT 토큰을 삭제함으로써 처리합니다.
        SecurityContextHolder.clearContext();
        return ResponseForm.of(ResponseCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공");
    }

}