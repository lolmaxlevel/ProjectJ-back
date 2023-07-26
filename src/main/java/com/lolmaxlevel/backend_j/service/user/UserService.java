package com.lolmaxlevel.backend_j.service.user;


import com.lolmaxlevel.backend_j.model.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(@NonNull String username);

    boolean addUser(@NonNull User user);
}
