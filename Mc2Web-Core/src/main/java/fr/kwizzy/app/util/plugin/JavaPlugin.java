package fr.kwizzy.app.util.plugin;



import fr.kwizzy.app.Mc2Web;

import java.io.File;


/**
 * Created by Alexis on 06/01/2017.
 * French author.
 */

public abstract class JavaPlugin extends org.bukkit.plugin.java.JavaPlugin implements Loader
{

    @Override
    public File getJar()
    {
        return getFile();
    }


    @Override
    public void sendImportantMessage(String message)
    {
        Mc2Web.toConsole("********************************");
        Mc2Web.toConsole("   " + message);
        Mc2Web.toConsole("********************************");
    }
}
