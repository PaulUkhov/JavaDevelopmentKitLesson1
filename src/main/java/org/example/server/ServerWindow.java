package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ServerWindow extends JFrame {
    private final MessageModel messageModel; // Модель сообщений, используется для обмена сообщениями между окнами
    private static final int WIDTH = 500;    // Ширина окна
    private static final int HEIGHT = 500;   // Высота окна

    private JButton btnStart;    // Кнопка запуска клиентского окна
    private JButton btnStop;     // Кнопка остановки (завершения работы приложения)
    private ClientGUI clientGUI; // Клиентское окно чата

    public ServerWindow(MessageModel messageModel) {
        this.messageModel = messageModel;

        // Настройка параметров окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрытие приложения при нажатии "крестика"
        setSize(WIDTH, HEIGHT);                 // Устанавливаем размеры окна
        setLocationRelativeTo(null);            // Центрируем окно на экране
        setTitle("Chat Server");                // Устанавливаем заголовок окна
        setResizable(false);                    // Запрещаем изменение размеров окна

        // Создаем кнопки для управления сервером
        btnStart = new JButton("Start"); // Кнопка для запуска клиентского окна
        btnStop = new JButton("Stop");   // Кнопка для завершения работы приложения

        // Обработчик кнопки "Start"
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Проверяем, создано ли уже клиентское окно
                if (clientGUI == null) {
                    // Если клиентское окно не создано, создаем новое
                    clientGUI = new ClientGUI(messageModel);
                    clientGUI = new ClientGUI(messageModel);
                    clientGUI.setVisible(true); // Отображаем клиентское окно
                } else {
                    // Если клиентское окно уже создано, показываем сообщение об ошибке
                    JOptionPane.showMessageDialog(ServerWindow.this,
                            "ClientGUI is already initialized!", // Текст сообщения
                            "Error",                            // Заголовок окна сообщения
                            JOptionPane.ERROR_MESSAGE);         // Тип сообщения (ошибка)
                }
            }
        });

        // Обработчик кнопки "Stop"
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Завершаем приложение при нажатии кнопки "Stop"
                System.exit(0);
            }
        });

        // Создаем нижнюю панель с кнопками "Start" и "Stop"
        JPanel panBottom = new JPanel();       // Панель для размещения кнопок
        panBottom.setLayout(new GridLayout(1, 2)); // Размещаем кнопки в одной строке (2 столбца)
        panBottom.add(btnStart);              // Добавляем кнопку "Start" на панель
        panBottom.add(btnStop);               // Добавляем кнопку "Stop" на панель

        // Добавляем панель с кнопками в нижнюю часть основного окна
        add(panBottom, BorderLayout.SOUTH);

        // Делаем окно видимым
        setVisible(true);
    }
}



