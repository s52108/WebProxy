package org.campus02.web;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;
    private WebProxy proxy;

    public ClientHandler(Socket client, WebProxy proxy) {
        this.client = client;
        this.proxy = proxy;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            String command;
            while ((command = bufferedReader.readLine()) != null){
                if(command.equals("bye")){
                    client.close();
                    return;l
                }
                String[] parts = command.split(" ");
                if(parts.length)
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
