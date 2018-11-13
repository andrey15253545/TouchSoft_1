package server.command.impl.dialog.starting;

import server.service.UserService;
import server.command.impl.Command;
import server.user.*;

public class StartDialogClientImpl extends StartDialog implements Command {

    public synchronized String execute(UserService userService) {
        return super.execute(userService,WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM,
                WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM);
    }
}