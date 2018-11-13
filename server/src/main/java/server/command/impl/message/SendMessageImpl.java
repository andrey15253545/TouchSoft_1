package server.command.impl.message;

import server.command.impl.Command;
import server.service.UserService;
import server.user.User;

import java.io.IOException;
import java.util.logging.Logger;

public class SendMessageImpl implements Command {

    private static final String MESSAGE_SEND = "message send";
    private static final String ERROR_MESSAGE = "message don't send";
    private static Logger log = Logger.getLogger(SendMessageImpl.class.getName());

    @Override
    public synchronized String execute(UserService userService) {
        User user = userService.getCurrentUser();
        User companion = user.getRoom().getCompanion();
        try {
            companion.getOut().writeUTF(user.getLastMessage());
            log.info(String.format("%s send message to %s",user.toString(), user.getRoom().getCompanion()));
            return MESSAGE_SEND;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR_MESSAGE;
        } catch (NullPointerException e) {
            return ERROR_MESSAGE;
        }
    }
}
