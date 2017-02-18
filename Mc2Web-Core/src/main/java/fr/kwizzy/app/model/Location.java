package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.Bukkit;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class Location implements Bean
{
    private double x;
    private double y;
    private double z;
    private float  yaw   = 0;
    private float  pitch = 0;
    private String world;

    public Location(org.bukkit.Location loc)
    {
        x     = loc.getX();
        y     = loc.getY  ();
        z     = loc.getZ();

        yaw   = loc.getYaw();
        pitch = loc.getPitch();
        world = loc.getWorld().getName();
    }

    public Location(double x, double y, double z, String world)
    {
        this.x     = x;
        this.y     = y;
        this.z     = z;
        this.world = world;
    }

    public Location()
    {
    }

    public org.bukkit.Location toBukkitLocation() {
        return new org.bukkit.Location(Bukkit.getWorld(world), x, y , z);
    }
}
