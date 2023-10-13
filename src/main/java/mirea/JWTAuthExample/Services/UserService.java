package mirea.JWTAuthExample.Services;

import lombok.RequiredArgsConstructor;
import mirea.JWTAuthExample.Domain.User;
import mirea.JWTAuthExample.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "added new user with username " + user.getUsername();
    }
}
