package by.bury.monitorsensors.mapper;


import by.bury.monitorsensors.dto.AuthenticationUserDto;
import by.bury.monitorsensors.model.Role;
import by.bury.monitorsensors.model.RoleNameEnum;
import by.bury.monitorsensors.model.User;

import java.util.Set;
import java.util.stream.Collectors;


public class AuthenticationUserMapper {


    public static AuthenticationUserDto userToAuthenticationUserDto(User user) {
        if (user == null) {
            return null;
        }
        Set<RoleNameEnum> roleNames = rolesToRoleNames(user.getRoles());
        return new AuthenticationUserDto(user.getEmail(), roleNames);
    }

    private  static Set<RoleNameEnum> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
