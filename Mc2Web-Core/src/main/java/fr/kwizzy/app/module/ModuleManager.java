package fr.kwizzy.app.module;


import fr.kwizzy.app.Mc2Web;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ModuleManager
{

    private ModuleLoader loader;

    public ModuleManager(ModuleLoader loader)
    {
        this.loader = loader;
    }

    /**
     * Gets an MineAPI's module.
     *
     * @param name Name of the module.
     *
     * @return Object of the module.
     */
    public Module getModule(String name)
    {
        return this.loader.getModules().get(name);
    }

    /**
     * Check is module enabled by name.
     *
     * @param name Name of the module.
     *
     * @return Module Enable value.
     */
    public boolean isModuleEnabled(String name)
    {
        return this.isModuleEnabled(getModule(name));
    }

    /**
     * Check is module enabled by module Object.
     *
     * @param module Object of the module.
     *
     * @return Module enable value.
     */
    public boolean isModuleEnabled(Module module)
    {
        return module != null && module.isEnabled();
    }

    public Module loadModule(File file)
    {
        try
        {
            Module module = this.loader.loadModule(file);
            return module;

        }
        catch (InvalidModuleException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void enableModule(Module module)
    {
        this.loader.enableModule(module);
    }

    public void enableModules()
    {
        this.getModules().stream().filter(module -> !this.isModuleEnabled(module)).forEach(this::enableModule);
    }

    public void disableModule(Module module)
    {
        this.loader.disableModule(module);
    }

    public void disableModules()
    {
        this.getModules().stream().filter(this::isModuleEnabled).forEach(this::disableModule);
    }

    public void forEach(Consumer<Module> m) {
        getModules().forEach(m);
    }

    /**
     * Lists the modules.
     *
     * @return All loaded modules.
     */
    public List<Module> getModules()
    {
        return this.loader.getModules().keySet().stream().map(this::getModule).collect(Collectors.toList());
    }

    public static void enableAllModules(Mc2Web i) {
        File folder = new File(i.getDataFolder(), "modules/");
        if (folder.exists())
        {
            if(folder.listFiles() == null || folder.listFiles().length == 0)
            {
                Mc2Web.toConsole(ChatColor.GOLD,  "0 Module(s) have been loaded");
                return;
            }
            int nbModule = 0;

            for (File file : folder.listFiles())
            {

                if (file.getName().endsWith(".jar") && file.isFile())
                {

                    Module module = Mc2Web.getModuleManager().loadModule(file);
                    if (module != null)
                    {
                        if (module.isEnableStartup())
                        {
                            Mc2Web.getModuleManager().enableModule(module);
                            if(module.isEnabled())
                                nbModule++;
                        }
                    }
                }
            }
            Mc2Web.toConsole(ChatColor.GOLD,  nbModule + " Module(s) have been loaded");
        }
        else
            folder.mkdirs();

    }
}