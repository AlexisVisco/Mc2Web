package fr.kwizzy.app.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexis on 06/02/2017.
 * French author.
 */

@Getter
public class World implements Bean
{
    private final boolean canGenerateStructures;
    private final boolean animalsAllowed;
    private final boolean monstersAllowed;
    private final boolean pvp;

    private final int     weatherDuration;
    private final int     seaLevel;
    private final int     ambientSpawnLimit;
    private final int     animalSpawnLimit;
    private final int     maxHeight;
    private final int     monstersSpawnLimit;
    private final int     animalsSpawnLimit;
    private final int     actualPlayers;

    private final String  name;
    private final String  uuid;
    private final String  difficulty;
    private final String  environement;

    private final long    ticksPerAnimalSpawns;
    private final long    ticksPerMonsterSpawns;

    private List<Player> playersOnWorld;

    public World(org.bukkit.World w)
    {
        canGenerateStructures = w.canGenerateStructures();
        name                  = w.getName();
        uuid                  = w.getUID().toString();
        animalsAllowed        = w.getAllowAnimals     ();
        monstersAllowed       = w.getAllowAnimals     ();
        pvp                   = w.getPVP();
        ambientSpawnLimit     = w.getAmbientSpawnLimit();
        animalSpawnLimit      = w.getAnimalSpawnLimit ();
        difficulty            = w.getDifficulty().name();
        environement          = w.getEnvironment().name();
        maxHeight             = w.getMaxHeight        ();
        monstersSpawnLimit    = w.getMonsterSpawnLimit();
        animalsSpawnLimit     = w.getAnimalSpawnLimit ();
        actualPlayers         = w.getPlayers().size();
        playersOnWorld        = w.getPlayers().stream().map(Player::new).collect(Collectors.toList());
        seaLevel              = w.getSeaLevel();
        weatherDuration       = w.getWeatherDuration();
        ticksPerAnimalSpawns  = w.getTicksPerAnimalSpawns ();
        ticksPerMonsterSpawns = w.getTicksPerMonsterSpawns();
    }
}
