package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.json.JSONObject;

/**
 * Created by Alexis on 15/02/2017.
 * French author.
 */
@Getter
public class Plugin implements Bean
{

    String name;
    JSONObject pluginDescription;

    public Plugin(org.bukkit.plugin.Plugin p)
    {
        name = p.getName();
        pluginDescription = new Description(p.getDescription()).toJson();
    }

    static class Description implements Bean{

        PluginDescriptionFile pluginDescription;

        Description(PluginDescriptionFile p)
        {
            this.pluginDescription = p;
        }


        JSONObject toJson()
        {
            return new JSONObject(g.toJson(pluginDescription));
        }
    }
}
