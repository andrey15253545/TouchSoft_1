package server.command.impl.exit;

import server.command.impl.Command;
import server.command.impl.dialog.ending.EndDialogClientImpl;
import server.service.UserService;

public class ExitClientInDialogImpl implements Command {
    @Override
    public synchronized String execute(UserService userService) {
        new EndDialogClientImpl().execute(userService);
        return new ExitLoggedInUserImpl().execute(userService);
    }
}
