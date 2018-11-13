package server.service;

import server.command.impl.dialog.ending.EndDialog;
import server.user.Room;
import server.user.State;
import server.user.User;
import server.user.WaitingUser;

import java.io.IOException;
import java.util.logging.Logger;

public enum DialogService {

    INSTANCE;
    private static Logger log = Logger.getLogger(EndDialog.class.getName());
    private static final String WAIT = "wait, please";
    private static final String CONNECTED = "dialog created";
    private static final String LOG_MESSAGE = "%s connected with %s";

    public String createDialog(WaitingUser listRequester, State newRequesterState, WaitingUser listCompanion, State newCompanionState) {
        if (listRequester.get().isEmpty() || listCompanion.get().isEmpty()){
            log.info(String.format("%s wait", listRequester.get().getFirst().toString()));
            return WAIT;
        }
        else {
            User requester = listRequester.get().remove();
            User companion = listCompanion.get().remove();
            requester.setRoom(new Room(companion));
            companion.setRoom(new Room(requester));
            requester.setState(newRequesterState);
            companion.setState(newCompanionState);
            try {
                companion.getOut().writeUTF(CONNECTED);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info(String.format(LOG_MESSAGE, requester, companion));
            return CONNECTED;
        }
    }

}
