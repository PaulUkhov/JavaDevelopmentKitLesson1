package org.example.server;

import java.util.ArrayList;
import java.util.List;

// Класс MessageModel отвечает за управление сообщениями и их рассылку слушателям
public class MessageModel {
    // Список слушателей, которые подписались на получение уведомлений о новых сообщениях
    private final List<MessageListener> listeners = new ArrayList<>();
    // Метод для уведомления о подключении

    /**
     * Метод для добавления слушателя.
     * Каждый объект, который реализует интерфейс MessageListener, может подписаться на получение сообщений.
     *
     * @param listener объект, который хочет получать уведомления о новых сообщениях
     */
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener); // Добавляем слушателя в список
    }

    /**
     * Метод для отправки сообщения.
     * Уведомляет всех подписанных слушателей о новом сообщении.
     *
     * @param message сообщение, которое нужно отправить всем слушателям
     */
    public void sendMessage(String message) {
        // Проходим по всем слушателям в списке и уведомляем их
        for (MessageListener listener : listeners) {
            listener.onMessageReceived(message); // Вызываем метод onMessageReceived у каждого слушателя
        }
    }
}

