package fr.kwizzy.app.model;

import lombok.Getter;

/**
 * Created by Alexis on 08/02/2017.
 * French author.
 */

@Getter
public class Block implements Bean
{


    private String   material;
    private String   biome    = "null";
    private byte     data     = 0;
    private Location loc;

    public Block(org.bukkit.block.Block b)
    {
        loc      = new Location(b.getLocation());
        material = b.getType().name();
        biome    = b.getBiome().name();
        data     = b.getData();
    }

    public Block() {}
}
