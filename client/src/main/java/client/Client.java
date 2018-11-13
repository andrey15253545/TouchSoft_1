package client;

import java.net.*;
import java.io.*;

public class Client {

    private static final String EXIT_URL = "/exit";
    private static final String ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 6666;
    private static final String ERROR_MESSAGE = "problems on the server";
    static DataInputStream in;

    public static void main(String[] ar) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, SERVER_PORT);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            InputData inputData = new InputData();
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

    public void run() {
        try{
            String inputLine;
            do {
                inputLine =Client.in.readUTF();
                if (!inputLine.equals(MESSAGE_SEND))
                    System.out.println(inputLine);
            }while (!inputLine.equalsIgnoreCase(EXIT_INPUT_LINE));
        } catch (IOException e) {
           System.err.print(ERROR_MESSAGE);
        }
    }
}

