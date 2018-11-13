package server.command.impl.dialog.ending;

import server.command.impl.Command;
import server.service.UserService;
import server.user.State;

public class EndDialogAgentImpl extends EndDialog implements Command{

    public synchronized String execute(UserService userService) {
        return super.execute(userService, State.AGENT, State.CLIENT);
    }
}
