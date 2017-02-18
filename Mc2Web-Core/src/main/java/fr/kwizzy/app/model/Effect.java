package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class Effect
{


    private final Integer amplifier;
    private final Integer duration;
    private final String effect;

    public Effect(PotionEffect e)
    {
        effect    = e.getType().getName();
        duration  = e.getDuration();
        amplifier = e.getAmplifier();
    }
}
