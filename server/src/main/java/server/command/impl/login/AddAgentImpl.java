package server.command.impl.login;

import server.command.impl.Command;
import server.service.UserService;
import server.user.*;

public class AddAgentImpl extends AddUser implements Command {

    private static final String LOGGED = "You logged as an agent";

    public synchronized String execute (UserService userService) {
        return super.execute(userService, State.AGENT, LOGGED);
    }
}
