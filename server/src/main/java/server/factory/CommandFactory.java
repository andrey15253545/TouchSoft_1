package server.factory;

import server.command.impl.Command;
import server.command.impl.dialog.ending.EndDialogAgentImpl;
import server.command.impl.dialog.ending.EndDialogClientImpl;
import server.command.impl.dialog.starting.StartDialogAgentImpl;
import server.command.impl.dialog.starting.StartDialogClientImpl;
import server.command.impl.exit.ExitAgentInDialogImpl;
import server.command.impl.exit.ExitClientInDialogImpl;
import server.command.impl.exit.ExitGuestImpl;
import server.command.impl.exit.ExitLoggedInUserImpl;
import server.command.impl.login.AddAgentImpl;
import server.command.impl.login.AddClientImpl;
import server.command.impl.logout.LogoutImpl;
import server.command.impl.message.SendMessageImpl;
import server.user.State;
import server.user.User;

import java.util.HashMap;
import java.util.Map;

public enum CommandFactory{

    INSTANCE;

    private class Key {

        private String url;
        private State state;

        Key(String url, State roles) {
            this.url = url;
            this.state = roles;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            return (url != null ? url.equals(key.url) : key.url == null) && state == key.state;
        }

        @Override
        public int hashCode() {
            int result = url != null ? url.hashCode() : 0;
            result = 31 * result + (state != null ? state.hashCode(): 0);
            return result;
        }
    }

    private Map<Key, Command> commands = new HashMap<>();

    CommandFactory() {
        commands.put(new Key("/a", State.GUEST), new AddAgentImpl());
        commands.put(new Key("/c", State.GUEST), new AddClientImpl());
        commands.put(new Key("/logout", State.CLIENT), new LogoutImpl());
        commands.put(new Key("/logout", State.AGENT), new LogoutImpl());
        commands.put(new Key("/exit", State.GUEST), new ExitGuestImpl());
        commands.put(new Key("/exit", State.CLIENT), new ExitLoggedInUserImpl());
        commands.put(new Key("/exit", State.AGENT), new ExitLoggedInUserImpl());
        commands.put(new Key("/exit", State.AGENT_IN_ROOM), new ExitAgentInDialogImpl());
        commands.put(new Key("/exit", State.CLIENT_IN_ROOM), new ExitClientInDialogImpl());
        commands.put(new Key("Send message", State.CLIENT_IN_ROOM), new SendMessageImpl());
        commands.put(new Key("Send message", State.AGENT_IN_ROOM), new SendMessageImpl());
        commands.put(new Key("/end", State.AGENT_IN_ROOM), new EndDialogAgentImpl());
        commands.put(new Key("/end", State.CLIENT_IN_ROOM), new EndDialogClientImpl());
        commands.put(new Key("/starting", State.AGENT), new StartDialogAgentImpl());
        commands.put(new Key("/starting", State.CLIENT), new StartDialogClientImpl());
    }

    public Command getCommand(String url, User user) {
        return commands.get(new Key(parse(url), user.getState()));
    }

    private String parse(String url) {
        if (url.charAt(0)=='/') return url;
        else return "Send message";
    }

}
