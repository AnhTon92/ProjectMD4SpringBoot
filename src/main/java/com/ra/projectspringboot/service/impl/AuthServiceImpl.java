package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.dto.request.LoginRequest;
import com.ra.projectspringboot.dto.request.RegisterRequest;
import com.ra.projectspringboot.dto.response.JwtResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Role;
import com.ra.projectspringboot.model.entity.User;
import com.ra.projectspringboot.repository.IUserRepository;
import com.ra.projectspringboot.security.jwt.JwtProvider;
import com.ra.projectspringboot.security.principal.UserDetailCustom;
import com.ra.projectspringboot.service.IAuthService;
import com.ra.projectspringboot.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse login(LoginRequest loginRequest) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException ex) {
            throw new CustomException("username or password is incorrect", HttpStatus.BAD_REQUEST);
        }

        UserDetailCustom userDetailCustom = (UserDetailCustom) authentication.getPrincipal();
        if (!userDetailCustom.getStatus()) {
            throw new CustomException("your account is blocked", HttpStatus.BAD_REQUEST);
        }

        String accessToken = jwtProvider.generateToken(userDetailCustom);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .username(userDetailCustom.getUsername())
                .email(userDetailCustom.getEmail())
                .fullName(userDetailCustom.getFullName())
                .status(userDetailCustom.getStatus())
                .avatar(userDetailCustom.getAvatar())
                .phone(userDetailCustom.getPhone())
                .address(userDetailCustom.getAddress())
                .is_deleted(userDetailCustom.getIs_deleted())
                .roles(userDetailCustom.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    /**
     * register
     *
     * @param registerRequest request register
     *                        handle register with request register
     */
    @Override
    public void register(RegisterRequest registerRequest) throws CustomException {
        Set<Role> roles = new HashSet<>();
        if (registerRequest.getRoles() == null || registerRequest.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            registerRequest.getRoles().forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        try {
                            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                        } catch (CustomException e) {
                            throw new RuntimeException(e);
                        }
                    case "moderator":
                        try {
                            roles.add(roleService.findByRoleName(RoleName.ROLE_MODERATOR));
                        } catch (CustomException e) {
                            throw new RuntimeException(e);
                        }
                    case "user":
                        try {
                            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                        } catch (CustomException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        throw new RuntimeException("role " + role + " not found");
                }
            });
        }

        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(roles)
                .status(true)
                .createdAt(new Date())
                .build();
        userRepository.save(user);
    }
}
