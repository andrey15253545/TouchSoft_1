package server.command.impl.login;

import org.junit.Test;
import org.junit.Before;
import server.service.UserService;
import server.user.State;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.assertEquals;

public class AddAgentImplTest {

    private UserService userService;
    private AddAgentImpl addAgent;

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
        addAgent = new AddAgentImpl();
    }

    @Test
    public void executeTest() throws Exception {
        String expected = "You logged as an agent";
        String actual = addAgent.execute(userService);
        assertEquals(expected, actual);
    }

    @Test
    public void executeTestState() {
        State expected = State.AGENT;
        addAgent.execute(userService);
        State actual = userService.getCurrentUser().getState();
        assertEquals(expected,actual);
    }

}