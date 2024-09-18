import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) {
        System.out.println("Server start.");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept();
             // каналы записи и чтения в сокета
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream())) {

            dataOutputStream.writeUTF("Введите ваше имя: ");
            // сервер ждёт получения данных от клиента
            String userName = dataInputStream.readUTF();
            dataOutputStream.flush();

            String ansver;
            dataOutputStream.writeUTF("Проверим не робот ли вы? Напишите: 12Q -> ");
            ansver = dataInputStream.readUTF();
            while (!ansver.equalsIgnoreCase("12Q")) {
                dataOutputStream.writeUTF("Введите ещё раз: 12Q -> ");
                ansver = dataInputStream.readUTF();
                dataOutputStream.flush();
            }
            dataOutputStream.writeUTF("Отлично " + userName + ", вы человек." + "\nВам больше 18 лет? (yes/no) -> ");
            ansver = dataInputStream.readUTF();
            if (ansver.equalsIgnoreCase("no")) {
                dataOutputStream.writeUTF(userName + ", добро пожаловать на Детский сайт. \nПриятного просмотра!\n");
                dataOutputStream.flush();
            } else if (ansver.equalsIgnoreCase("yes")) {
                dataOutputStream.writeUTF(userName + ", добро пожаловать на Взрослый сайт. \nХорошего дня!\n");
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF("Очень приятно " + userName + ". \nПока!\n");
                dataOutputStream.flush();
            }
//            dataOutputStream.writeUTF("Нажми Enter для выхода.\n");
//            dataOutputStream.flush();
            System.out.println("Channels and connections are closed.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The Server is stopped.");
    }
}