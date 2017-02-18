package fr.kwizzy.app.faction;

import fr.kwizzy.app.module.Module;
import fr.kwizzy.app.module.ModuleInformations;
import fr.kwizzy.app.module.Application;

/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */

public class Faction2Web extends Module
{


    private static Faction2Web instance;

    @Override
    public Application getApplication()
    {
        return new FactionRouter();
    }

    @Override
    public ModuleInformations initModuleInformations()
    {
        return null;
    }

    @Override
    public void onEnable()
    {
        setInstance(this);
    }

    @Override
    public void onDisable()
    {

    }

    public synchronized static void  setInstance(Faction2Web w) {
        instance = w;
    }

    public static Faction2Web getInstance()
    {
        return instance;
    }
}
