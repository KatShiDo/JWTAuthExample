package mirea.JWTAuthExample.Repositories;

import mirea.JWTAuthExample.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String username);
}
