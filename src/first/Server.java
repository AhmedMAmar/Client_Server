package first;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //private static String[] names = {"adama" , "traoure" , "amar"};
    //private static String[] adjs = {"the ruthless" , "white ghost" , "demar"};

    private static int port = 9090;

    private static ArrayList<clientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {

        ServerSocket Listener = new ServerSocket(port);

        while (true) {
            System.out.println("[SERVER] waiting for client...");
            Socket client = Listener.accept();
            System.out.println("[SERVER] connecter");
            clientHandler clientThread = new clientHandler(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }

    }

}
