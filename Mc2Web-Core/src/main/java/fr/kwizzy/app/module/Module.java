package fr.kwizzy.app.module;

import fr.kwizzy.app.Mc2Web;

/**
 * Created by Alexis on 15/02/2017.
 * French author.
 */


public abstract class Module
{

    protected Mc2Web             mc2Web;
    protected boolean            enableStartup = true;
    protected ModuleInformations informations;
    private   boolean            isEnabled;

    public String getName()
    {
        return this.informations.getName();
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract Application getApplication();

    public abstract ModuleInformations initModuleInformations();

    public void setModuleInformations(ModuleInformations description)
    {
        if (this.informations == null)
            this.informations = description;
    }

    public ModuleInformations getInformations()
    {
        return informations;
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public void setMc2Web(Mc2Web mc2Web)
    {
        this.mc2Web = mc2Web;
    }

    public ModuleManager getModuleManager()
    {
        return Mc2Web.getModuleManager();
    }

    public boolean isEnableStartup()
    {
        return this.enableStartup;
    }
}
