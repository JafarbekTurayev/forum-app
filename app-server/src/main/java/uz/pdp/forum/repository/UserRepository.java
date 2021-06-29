package uz.pdp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.forum.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
