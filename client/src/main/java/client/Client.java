package client;

import java.net.*;
import java.io.*;

public class Client {

    private static final String EXIT_URL = "/exit";
    private static final String ADDRESS = "127.0.0.2";
    private static final int SERVER_PORT = 6666;
    private static final String ERROR_MESSAGE = "problems on the server";
    private static Socket socket;

    public static void main(String[] ar) {

        System.out.println("asd".indexOf(" "));

        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            socket = new Socket(ipAddress, SERVER_PORT);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            InputData inputData = new InputData(socket);
            inputData.start();

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            do {
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();
            } while (!line.equalsIgnoreCase(EXIT_URL));
        } catch (IOException e) {
            System.err.print(ERROR_MESSAGE);
        }
    }
}
class InputData extends Thread {

    private static final String EXIT_INPUT_LINE = "exited";
    private static final String MESSAGE_SEND = "message send";
    private static final String ERROR_MESSAGE = "error";
    private  Socket socket;

    InputData(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            String read;
            do {
                read = in.readUTF();
                if (!read.equals(MESSAGE_SEND)) {
                    System.out.println(read);
                }
            }while (!read.equalsIgnoreCase(EXIT_INPUT_LINE));
        } catch (IOException e) {
           System.err.print(e);
           e.printStackTrace();
           System.err.print(ERROR_MESSAGE);
        }
    }
}
