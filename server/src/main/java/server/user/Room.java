package server.user;

public class Room {
    private User companion;

    public Room(User companion) {
        this.companion = companion;
    }

    public User getCompanion() {
        return companion;
    }

}
