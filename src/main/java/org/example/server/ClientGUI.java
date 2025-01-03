package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private final JTextArea messageArea;      // Текстовая область для отображения сообщений
    private final JTextField messageField;   // Поле для ввода сообщений
    private final MessageModel messageModel; // Модель сообщений, используемая для отправки и получения данных
//    private final JLabel statusLabel;         // Метка для статуса подключения
    public ClientGUI(MessageModel messageModel) {
        this.messageModel = messageModel; // Сохраняем ссылку на модель для обмена сообщениями

        // Настройка основного окна клиента
        setTitle("Chat Client");              // Устанавливаем заголовок окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Завершаем приложение при закрытии окна
        setSize(400, 500);                    // Устанавливаем размеры окна
        setLocationRelativeTo(null);          // Центрируем окно на экране
        setResizable(false);                  // Запрещаем изменение размеров окна

//        Создание панелей
        JPanel topPanel = new JPanel(new GridLayout(2, 3, 5, 5)); // Разметка сетки для полей ввода
        JPanel middlePanel = new JPanel(new BorderLayout()); // Для области сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout()); // Для ввода сообщения и кнопки отправки

//         Компоненты верхней панели
        JTextField ipField = new JTextField("Введите IP");
        JTextField portField = new JTextField("Введите PORT");
        JTextField usernameField = new JTextField("PВведите имя");
        JPasswordField passwordField = new JPasswordField("Введите пароль");
        JButton loginButton = new JButton("login");

        // Добавляем компоненты на верхнюю панель
        topPanel.add(ipField);
        topPanel.add(portField);
        topPanel.add(new JLabel()); // Пустой заполнитель для выравнивания
        topPanel.add(usernameField);
        topPanel.add(passwordField);
        topPanel.add(loginButton);

        // Создаем текстовую область для отображения сообщений
        messageArea = new JTextArea();        // Основное поле чата для истории сообщений
        messageArea.setEditable(false);       // Запрещаем редактирование текста пользователем
        JScrollPane scrollPane = new JScrollPane(messageArea); // Добавляем прокрутку для текстовой области

        // Создаем панель для ввода сообщений
        messageField = new JTextField();       // Поле для ввода текста сообщения
        JButton sendButton = new JButton("Send"); // Кнопка для отправки сообщения

        // Добавляем обработчик события нажатия кнопки "Send"
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText(); // Получаем текст из поля ввода
                if (!message.isEmpty()) {               // Проверяем, что поле ввода не пустое
                    messageModel.sendMessage(message);  // Отправляем сообщение через модель
                    messageField.setText("");           // Очищаем поле ввода
                }
            }
        });

        // Размещаем компоненты на панели ввода сообщений
        bottomPanel.add(messageField, BorderLayout.CENTER); // Поле ввода занимает центральную часть
        bottomPanel.add(sendButton, BorderLayout.EAST);     // Кнопка "Send" располагается справа

        // Добавляем метку для статуса подключения
//        statusLabel = new JLabel("Подключение..."); // Начальный текст
//        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Центрируем текст
//        statusLabel.setForeground(Color.BLUE); // Цвет текста
        // Размещаем основные компоненты в окне
        setLayout(new BorderLayout());           // Устанавливаем компоновку окна BorderLayout
        add(topPanel, BorderLayout.NORTH);       // Данные польщователя располагаются в верхней части окна
        add(scrollPane, BorderLayout.CENTER);    // Текстовая область занимает центральную часть окна
        add(bottomPanel, BorderLayout.SOUTH);    // Панель ввода сообщений располагается внизу
//        add(statusLabel, BorderLayout.CENTER);   // Метка со статусом подключается по центру

        // Добавляем слушателя для модели сообщений
        messageModel.addMessageListener(new MessageListener() {
            @Override
            public void onMessageReceived(String message) {
                messageArea.append(message + "\n"); // Добавляем новое сообщение в текстовую область
            }
        });

        // Делаем окно видимым
        setVisible(true);
    }
}

