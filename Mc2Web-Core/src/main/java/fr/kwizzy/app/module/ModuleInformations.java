package fr.kwizzy.app.module;

/**
 * Created by Alexis on 15/02/2017.
 * French author.
 */

import java.io.InputStream;
import java.util.Map;
import org.bukkit.plugin.InvalidDescriptionException;
import org.yaml.snakeyaml.Yaml;

public class ModuleInformations
{

    private String name;
    private String main;
    private String version;
    private String authors;

    public ModuleInformations(String name, String main, String version, String authors)
    {
        this.name = name;
        this.main = main;
        this.version = version;
        this.authors = authors;
    }

    public ModuleInformations(InputStream stream) throws InvalidDescriptionException
    {
        loadMap(asMap(new Yaml().load(stream)));
    }

    public String getName()
    {
        return this.name;
    }

    public String getMain()
    {
        return this.main;
    }

    public String getVersion()
    {
        return this.version;
    }

    public String getAuthorsInLine()
    {
        return this.authors;
    }

    public String[] getAuthors()
    {
        return this.authors.split(",");
    }

    private void loadMap(Map<?, ?> map) throws InvalidDescriptionException
    {
        try
        {
            this.name = map.get("name").toString();

            if (!this.name.matches("^[A-Za-z0-9 _.-]+$"))
            {
                throw new InvalidDescriptionException("name '" + this.name + "' contains invalid characters.");
            }
            this.name = this.name.replace(' ', '_');
        }
        catch (NullPointerException ex)
        {
            throw new InvalidDescriptionException(ex, "name is not defined");
        }
        catch (ClassCastException ex)
        {
            throw new InvalidDescriptionException(ex, "name is of wrong type");
        }
        try
        {
            this.version = map.get("version").toString();
        }
        catch (NullPointerException ex)
        {
            throw new InvalidDescriptionException(ex, "version is not defined");
        }
        catch (ClassCastException ex)
        {
            throw new InvalidDescriptionException(ex, "version is of wrong type");
        }
        try
        {
            this.main = map.get("main").toString();
            if (this.main.startsWith("org.bukkit.") && this.main.startsWith("org.spigotmc."))
            {
                throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
            }
        }
        catch (NullPointerException ex)
        {
            throw new InvalidDescriptionException(ex, "main is not defined");
        }
        catch (ClassCastException ex)
        {
            throw new InvalidDescriptionException(ex, "main is of wrong type");
        }

        if (map.get("authors") != null)
        {
            this.authors = map.get("authors").toString();
        }
    }

    @SuppressWarnings("rawtypes")
    private Map<?, ?> asMap(Object object) throws InvalidDescriptionException
    {
        if ((object instanceof Map))
        {
            return (Map) object;
        }
        throw new InvalidDescriptionException(object + " is not properly structured.");
    }
}
