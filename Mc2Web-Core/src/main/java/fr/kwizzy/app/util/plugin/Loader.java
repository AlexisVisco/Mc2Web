package fr.kwizzy.app.util.plugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by Alexis on 06/01/2017.
 * French author.
 */

public interface Loader
{

    void onEnable();
    void onDisable();
    File getJar();
    Logger getLogger();
    void sendImportantMessage(String message);

}
