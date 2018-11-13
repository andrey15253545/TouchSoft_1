package server.command.impl.logout;

import server.command.impl.Command;
import server.service.UserService;
import server.user.WaitingClientImpl;
import server.user.State;
import server.user.User;

public class LogoutImpl implements Command {

    private static final String LOGOUT = "Logged out";

    @Override
    public synchronized String execute(UserService userService) {
        User user = userService.getCurrentUser();
        user.setState(State.GUEST);
        return LOGOUT;
    }

}
