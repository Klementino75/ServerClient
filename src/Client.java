import java.io.*;
import java.net.Socket;

public class Client {
    public static final String HOST = "netology.homework";

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, Server.PORT);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            // проверка, что канал работает
            while (!socket.isOutputShutdown()) {
                    // получаем вопрос
                    System.out.print(dataInputStream.readUTF());
                    String clientCommand = bufferedReader.readLine();
                    if (clientCommand.isEmpty()) {
                        break;
                    }
                    // ввод данных с консоли в канал сокета сервера
                    dataOutputStream.writeUTF(clientCommand);
                    dataOutputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("The Client is stopped.");
        }
    }
}