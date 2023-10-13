package mirea.JWTAuthExample.Controllers;

import lombok.RequiredArgsConstructor;
import mirea.JWTAuthExample.Domain.AuthRequest;
import mirea.JWTAuthExample.Domain.User;
import mirea.JWTAuthExample.Services.JwtService;
import mirea.JWTAuthExample.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }
        else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }
}
