package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateUserDTO;
import com.kurenkievtimur.market.model.Role;
import com.kurenkievtimur.market.model.User;
import com.kurenkievtimur.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(CreateUserDTO userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.EMPLOYEE)
                .isActive(true)
                .build();
        userRepository.save(user);
    }
}
