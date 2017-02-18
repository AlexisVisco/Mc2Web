package fr.kwizzy.app.faction.bean;

import com.massivecraft.factions.entity.MPlayer;
import fr.kwizzy.app.model.Bean;

/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */

public class FPlayer implements Bean
{

    private String uuid;
    String faction, role, title;
    double power;
    boolean online;
    String name;

    public FPlayer(MPlayer player)
    {
        name = player.getName();
        uuid = player.getUuid().toString();
        faction = player.getFactionName();
        power = player.getPower();
        role = player.getRole().getName();
        title = player.getTitle();
        online = player.isOnline();
    }
}
