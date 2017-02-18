package fr.kwizzy.app.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis on 11/02/2017.
 * French author.
 *
 * Class that allows to manage a flow of data of
 * connection/disconnection of player, message and commands.
 */

@WebSocket
public class Console
{
    static List<Session> sessions = new ArrayList<>();

    String consoleMessage;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        
        sessions.add(user);
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        sessions.remove(user);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        consoleMessage(consoleMessage = message);
    }

    public static void consoleMessage(String consoleMessage) {
        sessions.stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("text", consoleMessage)
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
