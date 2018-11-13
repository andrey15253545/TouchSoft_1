package server.command.impl.dialog.starting;

import org.junit.Before;
import org.junit.Test;
import server.service.UserService;
import server.user.*;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class StartDialogTest {

    private static final String WAIT = "wait, please";
    private static final String CONNECTED = "dialog created";

    private StartDialog startDialog = new StartDialog();
    private UserService userService;
    private User client;

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
        client = new User(out);
        client.setState(State.CLIENT);
    }

    @Test
    public void executeAddInListTest() {
        startDialog.execute(userService, WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM,
                WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM);
        boolean condition = WaitingClientImpl.INSTANCE.get().contains(userService.getCurrentUser());
        WaitingClientImpl.INSTANCE.get().remove();
        assertTrue(condition);
    }

    @Test
    public void executeNotAddIfExistTest() {
        WaitingClientImpl.INSTANCE.get().addLast(userService.getCurrentUser());
        startDialog.execute(userService, WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM,
                WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM);
        int expected = 1;
        int actual = WaitingClientImpl.INSTANCE.get().size();
        WaitingClientImpl.INSTANCE.get().remove();
        assertEquals(expected,actual);
    }

    @Test
    public void executeWaitTest() throws Exception {
        String expected = WAIT;
        String actual = startDialog.execute(userService, WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM,
                WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM);
        WaitingClientImpl.INSTANCE.get().remove();
        assertEquals(expected, actual);
    }



    @Test
    public void executeConnectedTest() {
        String expected = CONNECTED;
        WaitingClientImpl.INSTANCE.get().addLast(client);
        String actual = startDialog.execute(userService, WaitingAgentImpl.INSTANCE, State.AGENT_IN_ROOM,
                WaitingClientImpl.INSTANCE, State.CLIENT_IN_ROOM);
        assertEquals(expected, actual);
    }
}