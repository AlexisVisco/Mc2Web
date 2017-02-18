package fr.kwizzy.app.faction.bean;

import com.massivecraft.factions.entity.FactionColl;
import fr.kwizzy.app.model.Bean;
import fr.kwizzy.app.model.Location;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */

public class Faction implements Bean
{

    private Location      home;
    private String        description, name;
    private double        power;
    private Set<String>   claimedWorld;
    private List<FPlayer> players;


    public Faction(com.massivecraft.factions.entity.Faction faction)
    {
        if(faction.getHome() != null)
            home = new Location(faction.getHome().asBukkitBlock().getLocation());
        if(faction.getDescription() != null)
            description = faction.getDescription();
        name = faction.getName();
        if(faction.getClaimedWorlds() != null)
            claimedWorld = faction.getClaimedWorlds();
        power = faction.getPower();
        players = faction.getMPlayers().stream().map(FPlayer::new).collect(Collectors.toList());

    }

    public Faction(String name)
    {
        this(FactionColl.get().getByName(name));
    }


}
