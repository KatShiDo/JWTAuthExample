package mirea.JWTAuthExample.Controllers;

import lombok.RequiredArgsConstructor;
import mirea.JWTAuthExample.Domain.User;
import mirea.JWTAuthExample.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        return userService.create(user);
    }
}
