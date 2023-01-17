package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String serverMessage = reader.readLine();
            System.out.println(serverMessage);

            System.out.println("Введите названия города с маленькой буквы");
            String cityName = scanner.nextLine();
            writer.println(cityName);
            writer.flush();
            System.out.println(reader.readLine());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
