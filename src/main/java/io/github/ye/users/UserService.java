package io.github.ye.users;

import io.github.ye.injections.Service;

@Service
public class UserService {
    public String getName() {
        return "user";
    }
}
