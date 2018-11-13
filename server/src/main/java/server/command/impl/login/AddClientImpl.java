package server.command.impl.login;

import server.command.impl.Command;
import server.service.UserService;
import server.user.State;

public class AddClientImpl extends AddUser implements Command {

    private static final String LOGGED = "You logged as a client";

    public synchronized String execute (UserService userService) {
        return super.execute(userService, State.CLIENT, LOGGED);
    }
}
