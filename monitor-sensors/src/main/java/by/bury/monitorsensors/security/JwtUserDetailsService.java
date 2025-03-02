package by.bury.monitorsensors.security;



import by.bury.monitorsensors.model.User;
import by.bury.monitorsensors.repository.UserRepository;
import by.bury.monitorsensors.security.jwt.JwtUser;
import by.bury.monitorsensors.security.jwt.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(JwtUserDetailsService.class.getName());

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username:" + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        logger.info(String.format("IN loadUserByUsername -  loaded username: %s successfully loaded", username));
        return jwtUser;
    }
}
