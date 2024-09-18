import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client0 {
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, Server0.PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("\nClient start.");
            writer.println("Client");
            System.out.println("Server ответил:\n" + reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
