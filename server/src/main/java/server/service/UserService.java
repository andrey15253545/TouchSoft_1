package server.service;

import server.user.User;

public class UserService {

    private User currentUser;

    public UserService(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
