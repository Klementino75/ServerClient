import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server0 {

    public static final Integer PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("\nServer start.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String infoFromClient = bufferedReader.readLine();
                System.out.printf("New connection accepted, port %s.\n",
                        serverSocket.getLocalPort());
                System.out.printf("Hi %s, your address is %s, port %s.\n",
                        infoFromClient, serverSocket.getLocalSocketAddress(), clientSocket.getPort());
                printWriter.printf("Привет от Server, %s! Твой LocalSocketAddress: %s. Твой Port: %s.\n",
                        infoFromClient, clientSocket.getLocalSocketAddress(), clientSocket.getPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
