package server.command.impl.dialog.ending;

import org.junit.Before;
import org.junit.Test;
import server.command.impl.dialog.starting.StartDialog;
import server.service.UserService;
import server.user.Room;
import server.user.State;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class EndDialogTest {

    private EndDialog endDialog= new EndDialog();
    private UserService userService;
    private User client;
    private User agent;

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        client = new User(out);
        client.setState(State.CLIENT_IN_ROOM);
        agent = new User(out);
        agent.setState(State.AGENT_IN_ROOM);
        client.setRoom(new Room(agent));
        agent.setRoom(new Room(client));
        userService = new UserService(client);
        endDialog.execute(userService, State.CLIENT, State.AGENT);
    }

    @Test
    public void executeClientChangeStateTest() throws Exception {
        State expected = State.CLIENT;
        State actual = client.getState();
        assertEquals(expected, actual);
    }

    @Test
    public void executeAgentChangeStateTest() throws Exception {
        State expected = State.AGENT;
        State actual = agent.getState();
        assertEquals(expected, actual);
    }

    @Test
    public void executeClientRoomIsNullTest() throws Exception {
        assertNull(client.getRoom());
    }

    @Test
    public void executeAgentRoomIsNullTest() throws Exception {
        assertNull(agent.getRoom());
    }

}
