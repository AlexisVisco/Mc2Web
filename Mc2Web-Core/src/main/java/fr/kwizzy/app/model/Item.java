package fr.kwizzy.app.model;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

public class Item implements Bean
{


    private int    id;
    private int    amount;
    private String type;
    private String name;
    private short  data;

    private Map<Enchantment, Integer> enchantments;

    Item(ItemStack item)
    {

        id     = item.getTypeId();
        type   = item.getType().name();
        data   = item.getDurability();
        amount = item.getAmount();

        if(!item.getEnchantments().isEmpty())
            enchantments = item.getEnchantments();
        else
            enchantments = new HashMap<>();

        if(item.hasItemMeta() && item.getItemMeta().hasDisplayName())
            name = item.getItemMeta().getDisplayName();
        else
            name = "";
    }
}
