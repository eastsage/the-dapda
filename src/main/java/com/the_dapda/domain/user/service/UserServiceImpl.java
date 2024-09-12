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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseForm register(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isPresent()) {
            return ResponseForm.of(ResponseCode.AUTH_REGISTER_DUPLICATE_ID, "이미 사용 중인 ID입니다.");
        }
        if(userRepository.findByNickname(userDto.getNickname()).isPresent()){
            return ResponseForm.of(ResponseCode.AUTH_REGISTER_DUPLICATE_NICKNAME, "이미 사용 중인 닉네임입니다.");
        }

        com.the_dapda.domain.user.entity.User user = new User();
        user.setUserId(userDto.getUserId());
        user.setId(userDto.getId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNickname(userDto.getNickname());

        userRepository.save(user);
        return ResponseForm.of(ResponseCode.AUTH_REGISTER_SUCCESS, "회원가입 성공");
    }

    public ResponseForm login(UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getId(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtProvider.createToken(userDto.getId(), roles);
            return ResponseForm.of(ResponseCode.AUTH_LOGIN_SUCCESS, token);
        } catch (Exception e) {
            return ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, "로그인 실패");
        }
    }

    public ResponseForm logout() {
        SecurityContextHolder.clearContext();
        return ResponseForm.of(ResponseCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공");
    }
}
