package server.command.impl.login;

import server.command.impl.dialog.ending.EndDialog;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.util.logging.Logger;

public class AddUser {

    private static Logger log = Logger.getLogger(EndDialog.class.getName());
    private static final String LOG_MESSAGE = "%s entered as %s";

    public String execute(UserService userService, State newState, String message){
        User user = userService.getCurrentUser();
        user.setState(newState);
        log.info(String.format(LOG_MESSAGE, user.toString(), newState.toString()));
        return message;
    }

}
