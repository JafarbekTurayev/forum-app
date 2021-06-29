package uz.pdp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.forum.entity.Question;
import uz.pdp.forum.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
