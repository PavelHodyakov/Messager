package com.messager;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by pavel on 13.07.17.
 */

public interface Store {

    public String GetMessageById(String id);

    /**
     * Метод добавляет сообщения
     * @param mes - сообщение
     */
    //void AddMessage(MessageImpl message);

    void AddMessage(String mes);

    /**
     * Получает сообщения для конкретного пользователя
     * @param Login - логин пользователя - получателя сообщения
     * @return - список непрочитанных сообщений для пользователя
     */
    String GetMessageForSystem(String Login);

    /**
     * Метод для получения всех непрочитанных сообщений
     * @return - список непрочитанных сообщений
     */
    List<MessageImpl> GetMessage();

    /**
     * Подтверждение доставки
     * возможно в качестве аргумента надо будет использовать какой-то признак
     * что именно это сообщение было прочитано
     * @return true/false в зависимости от того дошло сообщение или нет
     */
   // boolean ConfirmDelivery();

    @Path("/condel/{n}")
    void ConfirmDelivery(@PathParam("n") int n);

    /**
     * Подтверждение о простении
     * @return true/false в зависимости от того было прочитано сообщение или нет
     */
    //boolean ConfirmReading();

    @Path("/conread/{n}")
    void ConfirmReading(@PathParam("n") int n);
}

