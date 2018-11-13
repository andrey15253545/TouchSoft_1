package server.command.impl.dialog.starting;

import server.command.impl.Command;
import server.service.UserService;
import server.user.State;
import server.user.WaitingAgentImpl;
import server.user.WaitingClientImpl;

public class StartDialogAgentImpl extends StartDialog implements Command {

    public synchronized String execute(UserService userService) {
        return super.execute(userService,WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM,
                WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM);
    }
}
