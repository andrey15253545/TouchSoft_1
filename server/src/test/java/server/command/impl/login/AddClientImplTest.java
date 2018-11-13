package server.command.impl.login;

import org.junit.Before;
import org.junit.Test;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class AddClientImplTest {

    private UserService userService;
    private AddClientImpl addClient;

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
        addClient = new AddClientImpl();
    }

    @Test
    public void executeTest() throws Exception {
        String expected = "You logged as a client";
        String actual = addClient.execute(userService);
        assertEquals(expected, actual);
    }

    @Test
    public void executeTestState() {
        State expected = State.CLIENT;
        addClient.execute(userService);
        State actual = userService.getCurrentUser().getState();
        assertEquals(expected,actual);
    }

}