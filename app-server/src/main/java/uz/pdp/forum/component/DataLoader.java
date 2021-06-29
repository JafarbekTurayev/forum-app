package uz.pdp.forum.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.forum.entity.Role;
import uz.pdp.forum.entity.User;
import uz.pdp.forum.entity.enums.Permissons;
import uz.pdp.forum.repository.RoleRepository;
import uz.pdp.forum.repository.UserRepository;

import java.security.Permissions;
import java.util.Arrays;

import static uz.pdp.forum.entity.enums.Permissons.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Role adminRole = new Role();
            Permissons[] values = Permissons.values();
            adminRole.setName(Constants.ADMIN);
            adminRole.setPermissons(Arrays.asList(values));
            adminRole.setDescription("Sistema egasi");
            Role savedAdmin = roleRepository.save(adminRole);

            User admin = new User();
            admin.setEnabled(true);
            admin.setUsername("admin");
            admin.setFullName("ADmincha");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(savedAdmin);
            userRepository.save(admin);

            Role userRole = new Role();
            userRole.setName(Constants.USER);
            userRole.setPermissons(Arrays.asList(ADD_QUESTION, VIEW_QUESTION, VIEW_QUESTIONS,ADD_COMMENT,VIEW_COMMENT,VIEW_COMMENTS));
            userRole.setDescription("Account owner");
            Role save = roleRepository.save(userRole);

            User user = new User();
            user.setEnabled(true);
            user.setUsername("user");
            user.setFullName("Jack");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(save);
            userRepository.save(user);


            User user2 = new User();
            user2.setEnabled(true);
            user2.setUsername("userBek");
            user2.setFullName("Doe");
            user2.setPassword(passwordEncoder.encode("user456"));
            user2.setRole(save);
            userRepository.save(user2);

        }
    }
}
