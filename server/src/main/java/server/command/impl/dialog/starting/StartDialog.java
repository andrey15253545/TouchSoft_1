package server.command.impl.dialog.starting;

import server.service.DialogService;
import server.service.UserService;
import server.user.State;
import server.user.User;
import server.user.WaitingUser;

import java.util.LinkedList;

public class StartDialog {

    public String execute(UserService userService,
                          WaitingUser requester, State newRequesterState,
                          WaitingUser companion, State newCompanionState) {
        User user = userService.getCurrentUser();
        LinkedList<User> list = requester.get();
        if (!list.contains(user))
            list.addLast(user);
        return DialogService.INSTANCE.createDialog(requester, newRequesterState, companion, newCompanionState);
    }

}
