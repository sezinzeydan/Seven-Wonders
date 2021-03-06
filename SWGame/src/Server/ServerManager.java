package Server;

import Client.view.CreateGamePane;
import Server.ServerController.ClientHandler;
import Server.ServerController.ProgressManager;
import Server.ServerController.ServerControllerFacade;
import Server.model.ModelService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerManager {

    private final int NUM_OF_PLAYERS = 3;
    private final int PORT = 5346;
    private int counter = 0;
    private ServerSocket serverSocket;
    private String ipAddress;
    private List<ClientHandler> clientHandlers;
    private boolean ready = true;
    private boolean ready2;
    private boolean ready3;


    public ServerManager() throws IOException {
        ipAddress = InetAddress.getLocalHost().getHostAddress();
        clientHandlers = new ArrayList<>();
        serverSocket = new ServerSocket(PORT);
    }

    public void acceptConnections() throws IOException, InterruptedException {
        while (clientHandlers.size() < NUM_OF_PLAYERS) {
            Socket socket = null;
            try {

                socket = serverSocket.accept();
                CreateGamePane.getInstance().update(clientHandlers.size());


                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                // ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
                //ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
                ClientHandler c = new ClientHandler(input, output, socket, clientHandlers.size(), NUM_OF_PLAYERS);
                System.out.println(clientHandlers.size());
                clientHandlers.add(c);
                c.start();

            } catch (Exception e) {
                assert socket != null;
                socket.close();
                e.printStackTrace();
            }
        }
        ServerControllerFacade.getInstance().startGame();
        openGamePage();
        update();

        while(true){
            for(ClientHandler client : clientHandlers){
                ready = client.isReady() && ready;
                ready2 = client.nextAge && ready2;
                ready3 = client.endPane && ready3;
            }

            if(ready){
                Thread.sleep(200);
                ModelService.getInstance().rotateDecks();
                openGamePage();
                update();

                for(ClientHandler client : clientHandlers){
                    client.setReady(false);
                }
            }
            if (ready2){
                openGamePage();
                update();
                for(ClientHandler client : clientHandlers){
                    client.setNextAge(false);
                }
            }
            if (ready3){
                openEndPane();
                update();
                for(ClientHandler client : clientHandlers){
                    client.setEnd(false);
                }
            }
            ready = true;
            ready2 = true;
            ready3 = true;
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    private void openGamePage() throws IOException, InterruptedException {
        ProgressManager.getInstance().nextCycle(clientHandlers);
    }

    private void openEndPane() throws IOException, InterruptedException {
        for (ClientHandler client: clientHandlers )
            client.openEndPane();
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
