package server;

import server.command.impl.Command;
import server.factory.CommandFactory;
import server.service.UserService;
import server.user.User;

import java.io.*;
import java.util.logging.Logger;

public class RequestDispatcher extends Thread {

    private static final String EXIT_URL = "/exit";
    private static final String COMMAND_NOT_FOUND = "Command '%s' not found";
    private static final CommandFactory factory = CommandFactory.INSTANCE;
    private static final String LOG_MESSAGE_CONNECT = "%s entered";
    private static Logger log = Logger.getLogger(RequestDispatcher.class.getName());

    private UserService userService;
    private DataInputStream in;

    public RequestDispatcher(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.userService  = new UserService(new User(out));
        log.info(String.format(LOG_MESSAGE_CONNECT, userService.getCurrentUser().toString()));
    }

    @Override
    public void run() {
        try {
            String url;
            do {
                DataOutputStream out = userService.getCurrentUser().getOut();
                url = in.readUTF();
                userService.getCurrentUser().setLastMessage(url);
                Command command = factory.getCommand(url, userService.getCurrentUser());
                if (command != null) {
                    String result = command.execute(userService);
                    out.writeUTF(result);
                } else {
                    out.writeUTF(String.format(COMMAND_NOT_FOUND, url));
                }
            } while (!url.equalsIgnoreCase(EXIT_URL));
        } catch (IOException e) {
            factory.getCommand(EXIT_URL, userService.getCurrentUser()).execute(userService);
        }
    }
}
