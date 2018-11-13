package server.command.impl.dialog.ending;

import server.Server;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.io.IOException;
import java.util.logging.Logger;

public class EndDialog {

    private static final String DIALOG_ENDED = "dialog ended";
    private static Logger log = Logger.getLogger(EndDialog.class.getName());
    private static final String LOG_MESSAGE = "dialog %s with %s ended";

    public String execute(UserService userService, State newRequesterState, State newCompanionState) {
        User requester = userService.getCurrentUser();
        User companion = requester.getRoom().getCompanion();
        try {
            companion.getOut().writeUTF(DIALOG_ENDED);
        } catch (IOException e) {
            log.info(e.toString());
        }
        requester.setRoom(null);
        companion.setRoom(null);
        requester.setState(newRequesterState);
        companion.setState(newCompanionState);
        log.info(String.format(LOG_MESSAGE,requester.toString(),companion.toString()));
        return DIALOG_ENDED;
    }

}
