package Server;

import Server.ServerController.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerManager {
    private final int NUM_OF_PLAYERS = 2;
    private final int PORT = 5553;

    private ServerSocket serverSocket;
    private String ipAddress;
    private List<ClientHandler> clientHandlers;


    public ServerManager() throws IOException {
        ipAddress = InetAddress.getLocalHost().getHostAddress();
        clientHandlers = new ArrayList<>();
        serverSocket = new ServerSocket(PORT);
    }

    public void acceptConnections() throws IOException {
        while (clientHandlers.size() < NUM_OF_PLAYERS) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();

                System.out.println("New Client Is Connected");

                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                // ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
                //ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("in ServerManager: method: acceptConnections");
                ClientHandler c = new ClientHandler(input, output, /*inputObject, outputObject, */ socket, clientHandlers.size() - 1);
                clientHandlers.add(c);
                c.start();
            } catch (Exception e) {
                assert socket != null;
                System.out.println("connection failed");
                socket.close();
                e.printStackTrace();
            }
        }
        openGamePage();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    private void openGamePage() throws IOException {
        for (ClientHandler c : clientHandlers) {
            c.openGamePage();
        }
    }

    public void update() throws IOException {
        for (ClientHandler c : clientHandlers) {
            c.update();
        }
    }

    public void onDisconnection() {
    }

    public String encryptKey(String ipAddress) {
        /*TODO() change*/
        return ipAddress;
    }

}