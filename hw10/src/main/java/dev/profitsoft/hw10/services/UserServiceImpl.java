package dev.profitsoft.hw10.services;

import dev.profitsoft.hw10.data.UserData;
import dev.profitsoft.hw10.dtos.UserSaveDto;
import dev.profitsoft.hw10.exceptions.UserIdNotFoundException;
import dev.profitsoft.hw10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String saveUser(UserSaveDto userSaveDto) {
        UserData user = convertToUserData(userSaveDto);

        return userRepository.insert(user).getId();
    }

    @Override
    public String updateUser(UserData user) {
        return userRepository.save(user).getId();
    }

    @Override
    public UserData findUserById(String id) throws UserIdNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public UserData findUserByEmail(String email) {
        if (!existsWithEmail(email)) {
            throw new UsernameNotFoundException("User with email '%s' not found".formatted(email));
        }

        return userRepository.findByEmail(email).get(0);
    }

    @Override
    public boolean existsWithEmail(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }

    private UserData convertToUserData(UserSaveDto userSaveDto) {
        UserData user = new UserData();

        user.setEmail(userSaveDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSaveDto.getPassword()));
        user.setRole(userSaveDto.getRole());
        user.setEnabled(userSaveDto.isEnabled());
        user.setBooksIds(userSaveDto.getBooksIds());

        return user;
    }
}