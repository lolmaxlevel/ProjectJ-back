package com.lolmaxlevel.backend_j.service.user;


import com.lolmaxlevel.backend_j.model.User;
import com.lolmaxlevel.backend_j.repository.UsersRepository;
import com.lolmaxlevel.backend_j.security.UserPasswordUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Override
    public Optional<User> getUser(@NonNull String username) {
        final var user = usersRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean addUser(@NonNull User user) {
        if (usersRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(UserPasswordUtil.hashPassword(user.getPassword()));
        usersRepository.save(user);
        return true;
    }
}
