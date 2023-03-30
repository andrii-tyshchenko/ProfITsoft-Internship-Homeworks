package dev.profitsoft.hw10.services;

import dev.profitsoft.hw10.data.Role;
import dev.profitsoft.hw10.data.UserData;
import dev.profitsoft.hw10.repositories.RoleRepository;
import dev.profitsoft.hw10.repositories.UserRepository;
//import jakarta.annotation.PostConstruct; - в новіших версіях SpringBoot?
//import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserData regularUser = new UserData();
        regularUser.setEmail("john_doe@fictionmail.com");
        regularUser.setPassword(passwordEncoder.encode("password"));
        regularUser.setRole("USER");
        regularUser.setEnabled(true);
        regularUser.setBooksIds(List.of());
        userRepository.save(regularUser);

        UserData admin = new UserData();
        admin.setEmail("admin@fictionmail.com");
        admin.setPassword(passwordEncoder.encode("admin_password"));
        admin.setRole("ADMIN");
        admin.setEnabled(true);
        admin.setBooksIds(List.of());
        userRepository.save(admin);
    }

    @PreDestroy
    public void preDestroy() {
        userRepository.deleteByEmail("john_doe@fictionmail.com");
        userRepository.deleteByEmail("admin@fictionmail.com");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserData> users = userRepository.findByEmail(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User with email '%s' not found".formatted(username));
        }

        return convertToUserDetails(users.get(0));

    }

    private UserDetails convertToUserDetails(UserData user) {
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(collectAuthorities(user.getRole()))
                .disabled(!user.isEnabled())
                .build();
    }

    private List<GrantedAuthority> collectAuthorities(String role) {
        return roleRepository.getRole(role)
                .map(Role::getPrivileges)
                .stream().flatMap(Collection::stream)
                .map(priv -> (GrantedAuthority)new SimpleGrantedAuthority("PRIV_" + priv))
                .toList();
    }
}