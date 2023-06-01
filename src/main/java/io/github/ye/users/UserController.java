package io.github.ye.users;

import io.github.ye.controllers.Controller;
import io.github.ye.controllers.Get;
import io.github.ye.injections.Inject;

@Controller("/api/v1/user")
public class UserController {
    @Inject()
    public UserService userService = null;

    @Get()
    public String user() {
        return this.userService.getName();
    }
}
