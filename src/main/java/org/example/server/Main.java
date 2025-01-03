package org.example.server;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        // Создаем модель сообщений
        MessageModel messageModel = new MessageModel();

        // Создаем серверное окно
        ServerWindow serverWindow = new ServerWindow(messageModel);

//         Если требуется, можно сразу открыть два клиентских окна
//        ClientGUI client1 = new ClientGUI(messageModel);
//        client1.setVisible(true);
//
//        ClientGUI client2 = new ClientGUI(messageModel);
//        client2.setVisible(true);
    }
}



