package server.user;

import java.io.DataOutputStream;

public class User {

    private State state;
    private Room room;
    private DataOutputStream out;
    private String lastMessage;

    public User(DataOutputStream out) {
        this.state = State.GUEST;
        this.out = out;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public State getState() {
        return state;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
