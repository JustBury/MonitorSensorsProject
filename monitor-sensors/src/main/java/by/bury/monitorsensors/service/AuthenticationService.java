package by.bury.monitorsensors.service;


import by.bury.monitorsensors.dto.AuthenticationRequestDto;
import by.bury.monitorsensors.dto.AuthenticationUserDto;

public interface AuthenticationService {

    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);
}
