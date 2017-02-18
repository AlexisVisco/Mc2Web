package fr.kwizzy.app.model;

import fr.kwizzy.app.util.Util;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class ComplexPlayer extends Player implements Bean
{


    private final Integer    hunger;
    private final Integer    level;
    private final Double     health;
    private final Double     healthScale;
    private final String     gamemode;
    private final String     ip;
    private final String     dateFirstPlayed;
    private final Float      exhaustion;
    private final Float      exp;
    private final Float      saturation;
    private final Float      flySpeed;
    private final Float      walkSpeed;
    private final Boolean    isSneaking;
    private final Boolean    isFlying;
    private final Boolean    isSprinting;
    private final Boolean    isOnGround;
    private final Boolean    isOp;
    private final Boolean    isWhitelist;
    private final Location   location;
    private       Item       itemInHand;
    private final Enderchest enderchest;

    private final HashMap<Integer, Item> inventory = new HashMap<>();
    private final ArrayList<Effect> activeEffect = new ArrayList<>();


    public ComplexPlayer(org.bukkit.entity.Player p)
    {
        super(p);

        health          = p.getHealth     ();
        dateFirstPlayed = Util.parseDate(new Date(p.getFirstPlayed()));
        gamemode        = p.getGameMode().name();
        isOp            = p.isOp();
        isWhitelist     = p.isWhitelisted();
        ip              = p.getAddress().toString();
        healthScale     = p.getHealthScale();
        hunger          = p.getFoodLevel();
        exp             = p.getExp       ();
        level           = p.getLevel();
        exhaustion      = p.getExhaustion();
        saturation      = p.getSaturation();
        flySpeed        = p.getFlySpeed ();
        walkSpeed       = p.getFlySpeed ();
        isSneaking      = p.isSneaking();
        isSprinting     = p.isSprinting();
        isFlying        = p.isFlying();
        isOnGround      = p.isOnGround();
        location        = new Location(p.getLocation());
        enderchest      = new Enderchest(p);

        for (int i = 0; i < p.getInventory().getContents().length; i++) {
            ItemStack itemStack = p.getInventory().getContents()[i];
            if(itemStack != null)
                inventory.put(i, new Item(itemStack));
        }
        if(p.getItemInHand() != null) {
            itemInHand = new Item(p.getItemInHand());
        } 

        p.getActivePotionEffects().forEach(e -> activeEffect.add(new Effect(e)));
    }
}
