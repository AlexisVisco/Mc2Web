package fr.kwizzy.app.model;

import fr.kwizzy.app.Mc2Web;
import lombok.Getter;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class ServerInfo implements  Bean
{

    private final String  spigotVersion;
    private final String  version;
    private final String  motd;
    private final Integer players;
    private final Integer maxPlayers;

    public ServerInfo()
    {
        motd          = getServer().getMotd();
        version       = getVersion();
        spigotVersion = getServer().getVersion();
        maxPlayers    = getServer().getMaxPlayers();
        players       = Bukkit.getOnlinePlayers().size();
    }

    private String getVersion() {
        String a = getServer().getClass().getPackage().getName();
        return a.substring(a.lastIndexOf('.') + 1);
    }


}
