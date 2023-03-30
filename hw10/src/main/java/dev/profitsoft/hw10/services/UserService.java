package dev.profitsoft.hw10.services;

import dev.profitsoft.hw10.data.UserData;
import dev.profitsoft.hw10.dtos.UserSaveDto;
import dev.profitsoft.hw10.exceptions.UserIdNotFoundException;

public interface UserService {
    String saveUser(UserSaveDto userSaveDto);

    String updateUser(UserData user);

    UserData findUserById(String id) throws UserIdNotFoundException;

    UserData findUserByEmail(String email);

    boolean existsWithEmail(String email);
}