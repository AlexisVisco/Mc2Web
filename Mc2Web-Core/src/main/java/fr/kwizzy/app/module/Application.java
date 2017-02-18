package fr.kwizzy.app.module;


import fr.kwizzy.app.back.WebServer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */
@EqualsAndHashCode
@ToString
@Getter
public abstract class Application
{
    protected static WebServer server = fr.kwizzy.app.webserver.Application.getWebServer();

    private String[] plugins;
    private String[] requiredPlugins;

    public Application(String[] plugins, String[] requiredPlugin)
    {
        this.plugins = plugins;
        this.requiredPlugins = requiredPlugin;
    }

    public abstract void injectRoutes();


}
