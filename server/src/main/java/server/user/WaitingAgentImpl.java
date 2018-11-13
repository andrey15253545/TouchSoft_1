package server.user;

import java.util.LinkedList;

public enum WaitingAgentImpl implements WaitingUser {

    INSTANCE;

    private LinkedList<User> users = new LinkedList<User>();

    public LinkedList<User> get() {
        return users;
    }

}
