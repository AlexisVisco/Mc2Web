package fr.kwizzy.app.model;

import lombok.Getter;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class Player implements Bean
{
    private final String uuid;
    private final String name;
    private final String displayName;

    public Player(org.bukkit.entity.Player player)
    {
        this.uuid        = player.getUniqueId().toString();
        this.name        = player.getName();
        this.displayName = player.getDisplayName();
    }

    public Player(String uuid, String name, String displayName)
    {
        this.uuid        = uuid;
        this.name        = name;
        this.displayName = displayName;
    }
}
