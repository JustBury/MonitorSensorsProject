package by.bury.monitorsensors.service.impl;


import by.bury.monitorsensors.dto.AuthenticationRequestDto;
import by.bury.monitorsensors.dto.AuthenticationUserDto;
import by.bury.monitorsensors.model.User;
import by.bury.monitorsensors.repository.UserRepository;
import by.bury.monitorsensors.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import by.bury.monitorsensors.mapper.AuthenticationUserMapper;

import java.util.logging.Logger;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    private final UserRepository userRepository;


    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email());
        if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            AuthenticationUserDto authenticationUserDto = AuthenticationUserMapper.userToAuthenticationUserDto(user);
            logger.info(String.format("IN findByEmailAndPassword - authenticationUserDto: %s found by email: %s", authenticationUserDto,
                    authenticationUserDto.email()));
            return authenticationUserDto;
        } else {
            logger.info("IN findByEmailAndPassword - Invalid username or password");
            return null;
        }
    }
}
