package by.bury.monitorsensors.dto;

import by.bury.monitorsensors.model.RoleNameEnum;

import java.util.Set;

public record AuthenticationUserDto(
        String email,
        Set<RoleNameEnum> roleNames) {
}