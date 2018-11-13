package server.command.impl.logout;

import org.junit.Before;
import org.junit.Test;
import server.command.impl.dialog.starting.StartDialog;
import server.command.impl.login.AddAgentImpl;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class LogoutImplTest {

    private UserService userService;
    private LogoutImpl logout = new LogoutImpl();
    private static final String LOGOUT = "Logged out";

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
    }

    @Test
    public void executeStateTest() throws Exception {
        State expected = State.GUEST;
        logout.execute(userService);
        State actual = userService.getCurrentUser().getState();
        assertEquals(expected, actual);
    }

    @Test
    public void executeTest() {
        String actual = LOGOUT;
        String expected = logout.execute(userService);
        assertEquals(expected, actual);
    }



}