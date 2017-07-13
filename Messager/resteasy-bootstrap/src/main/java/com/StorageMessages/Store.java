package com.StorageMessages;

import java.util.List;

/**
 * Created by pavel on 13.07.17.
 */
public interface Store {
    /**
     * Метод добавляет сообщения
     * @param message - сообщение
     */
    void AddMessage(Message message);

    /**
     * Получает сообщения для конкретного пользователя
     * @param Login - логин пользователя - получателя сообщения
     * @return - список непрочитанных сообщений для пользователя
     */
    List<Message> GetMessageForSystem(String Login);

    /**
     * Метод для получения всех непрочитанных сообщений
     * @return - список непрочитанных сообщений
     */
    List<Message> GetMessage();

    /**
     * Подтверждение доставки
     * возможно в качестве аргумента надо будет использовать какой-то признак
     * что именно это сообщение было прочитано
     * @return true/false в зависимости от того дошло сообщение или нет
     */
    boolean ConfirmDelivery();

    /**
     * Подтверждение о простении
     * @return true/false в зависимости от того было прочитано сообщение или нет
     */
    boolean ConfirmReading();

}

