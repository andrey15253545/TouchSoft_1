package server.command.impl.exit;

import server.command.impl.Command;
import server.command.impl.logout.LogoutImpl;
import server.service.UserService;

public class ExitLoggedInUserImpl implements Command {
    @Override
    public synchronized String execute(UserService userService) {
        new LogoutImpl().execute(userService);
        return new ExitGuestImpl().execute(userService);
    }
}
