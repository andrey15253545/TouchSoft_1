package server.command.impl.login;

import org.junit.Before;
import org.junit.Test;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class AddUserTest {

    private UserService userService;
    private AddUser addUser;

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
        addUser = new AddUser();
    }

    @Test
    public void executeTest() throws Exception {
        String expected = "AA";
        String actual = addUser.execute(userService, State.GUEST, expected);
        assertEquals(expected, actual);
    }

    @Test
    public void executeTestState() {
        State expected = State.CLIENT;
        addUser.execute(userService, expected, "");
        State actual = userService.getCurrentUser().getState();
        assertEquals(expected, actual);
    }
}