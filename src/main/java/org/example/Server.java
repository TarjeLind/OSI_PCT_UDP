package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started");
        try (ServerSocket server = new ServerSocket(8989)) {

            String serverMessage = "???";
            String cityStartsWith = "";
            while (true) {
                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    if (serverMessage.equals("???")) {
                        writer.println(serverMessage);
                        writer.flush();

                        String cityName = reader.readLine();
                        if (!cityName.isBlank()) {
                            cityStartsWith = String.valueOf(cityName.charAt(cityName.length() - 1));
                            serverMessage = "OK";
                            writer.println(serverMessage);
                            writer.flush();
                            System.out.println("server message: " + serverMessage);
                        }
                    } else {
                        writer.println("Город начинается с буквы: " + cityStartsWith);
                        writer.flush();

                        String cityName = reader.readLine();
                        String cityStartsWithNew = String.valueOf(cityName.charAt(0));
                        if (!cityName.isBlank() && cityStartsWith.equals(cityStartsWithNew)) {
                            cityStartsWith = String.valueOf(cityName.charAt(cityName.length() - 1));
                            serverMessage = "OK";
                        } else {
                            serverMessage = "NOT OK";
                        }
                        writer.println(serverMessage);
                        writer.flush();
                        System.out.println("server message: " + serverMessage);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}