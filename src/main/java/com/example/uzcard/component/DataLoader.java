package com.example.uzcard.component;
import com.example.uzcard.consts.RoleName;
import com.example.uzcard.entity.Roles;
import com.example.uzcard.entity.User;
import com.example.uzcard.repositories.RoleRepository;
import com.example.uzcard.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final String initialMode="always";
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("")) {
            Roles service = new Roles();
            service.setRoleName(RoleName.ROLE_SERVICE);
            Roles save = roleRepository.save(service);
            Set<Roles> roles = new HashSet<>();
            roles.add(save);

            User user1 = new User();
            user1.setPassword(passwordEncoder.encode("54321"));
            user1.setRoles(roles);
            user1.setPhoneNumber("910012048");
            user1.setEmail("ilyos.khurozov@upay.uz");
            user1.setFirstname("Ilyos");
            user1.setLastname("Xo'rozov");
            userRepository.save(user1);
        }

    }
}
