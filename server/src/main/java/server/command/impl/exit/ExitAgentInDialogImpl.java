package server.command.impl.exit;

import server.command.impl.Command;
import server.command.impl.dialog.ending.EndDialogAgentImpl;
import server.service.UserService;

public class ExitAgentInDialogImpl implements Command {
    @Override
    public synchronized String execute(UserService userService) {
        new EndDialogAgentImpl().execute(userService);
        return new ExitLoggedInUserImpl().execute(userService);
    }
}
