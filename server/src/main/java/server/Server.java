package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    private static final int port = 6666;
    private static Logger log = Logger.getLogger(Server.class.getName());


    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                Socket socket = ss.accept();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                RequestDispatcher requestDispatcher = new RequestDispatcher(in, out);
                requestDispatcher.start();
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
    }
}
