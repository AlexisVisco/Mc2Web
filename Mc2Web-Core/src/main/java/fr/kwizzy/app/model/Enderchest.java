package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

@Getter
public class Enderchest implements Bean
{

    private final HashMap<Integer, Item> items = new HashMap<>();

    public Enderchest(org.bukkit.entity.Player p)
    {
        for (int i = 0; i < p.getEnderChest().getContents().length; i++) {
            ItemStack itemStack = p.getEnderChest().getContents()[i];
            if(itemStack != null)
                items.put(i, new Item(itemStack));
        }


    }
}
