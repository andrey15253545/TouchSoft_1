package server.command.impl.message;

import org.junit.Before;
import org.junit.Test;
import server.command.impl.logout.LogoutImpl;
import server.service.UserService;
import server.user.Room;
import server.user.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class SendMessageImplTest {

    private static final String MESSAGE_SEND = "message send";
    private static final String ERROR_MESSAGE = "message don't send";

    private UserService userService;
    private SendMessageImpl sendMessage = new SendMessageImpl();

    @Before
    public void setUp() throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(System.out));
        userService = new UserService(new User(out));
        userService.getCurrentUser().setLastMessage("");
        userService.getCurrentUser().setRoom(new Room(new User(out)));
    }

    @Test
    public void executeSendTest() throws Exception {
        String actual = sendMessage.execute(userService);
        String expected = MESSAGE_SEND;
        assertEquals(expected, actual);
    }

    @Test
    public void executeNotSendTest() throws Exception {
        userService.getCurrentUser().setRoom(new Room(new User(null)));
        String actual = sendMessage.execute(userService);
        String expected = ERROR_MESSAGE;
        assertEquals(expected, actual);
    }



}