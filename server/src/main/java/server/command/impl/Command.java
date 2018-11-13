package server.command.impl;

import server.service.UserService;

public interface Command {
    String execute(UserService userService);
}
