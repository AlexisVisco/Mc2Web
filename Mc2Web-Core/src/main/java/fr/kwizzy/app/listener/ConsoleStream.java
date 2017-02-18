package fr.kwizzy.app.listener;

import fr.kwizzy.app.util.Util;
import fr.kwizzy.app.controller.Console;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Date;

public class ConsoleStream implements Listener
{
    @EventHandler
    public void message(AsyncPlayerChatEvent e) {
        Console.consoleMessage("{" + now() + "} [PLAYER] [CHAT] " + e.getPlayer().getName() + ": " + e.getMessage());
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
        Console.consoleMessage("{" + now() + "} [PLAYER] [COMMAND] " + e.getPlayer().getName() + ": " + e.getMessage());
    }

    @EventHandler
    public void command(ServerCommandEvent e) {
        Console.consoleMessage("{" + now() + "} [CONSOLE] [COMMAND] " + e.getCommand());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Console.consoleMessage("{" + now() + "} [PLAYER] [QUIT GAME] " + e.getPlayer().getName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Console.consoleMessage("{" + now() + "} [PLAYER] [JOIN GAME] " + e.getPlayer().getName());
    }
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Console.consoleMessage("{" + now() + "} [PLAYER] [KICKER] " + e.getPlayer().getName() + ": " + e.getReason());
    }

    public String now() {return Util.parseDate(new Date());}
}