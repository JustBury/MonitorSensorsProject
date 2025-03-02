package by.bury.monitorsensors.controller;

import by.bury.monitorsensors.dto.AuthenticationRequestDto;
import by.bury.monitorsensors.dto.AuthenticationUserDto;
import by.bury.monitorsensors.security.jwt.JwtTokenProvider;
import by.bury.monitorsensors.service.impl.AuthenticationServiceImpl;
import by.bury.monitorsensors.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Authentication Controller", description = "API for working with authentication")
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationApiController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationApiController(AuthenticationServiceImpl authenticationService, JwtTokenProvider jwtTokenProvider){
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authenticationService.findByEmailAndPassword(requestDto);
        if (user == null) {
            throw new BadRequestException("Wrong email or password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("username", user.email());
        response.put("token", jwtTokenProvider.createToken(user));
        return ResponseEntity.ok(response);
    }
}
