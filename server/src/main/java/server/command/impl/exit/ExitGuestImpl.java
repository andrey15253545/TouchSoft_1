package server.command.impl.exit;

import server.command.impl.Command;
import server.service.UserService;

public class ExitGuestImpl implements Command{

    private static final String EXITED = "exited";

    @Override
    public String execute(UserService userService) {
        return EXITED;
    }
}
