package fr.kwizzy.app.model;

import fr.kwizzy.app.Mc2Web;
import fr.kwizzy.app.module.ModuleManager;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexis on 17/02/2017.
 * French author.
 */

@Getter
public class Module implements Bean
{
    private String   name;
    private String[] supportedPlugins;
    private String[] requiredPlugins;
    private boolean  isEnabled;

    public Module(fr.kwizzy.app.module.Module mod)
    {
        name             = mod.getName();
        supportedPlugins = mod.getApplication().getPlugins();
        requiredPlugins  = mod.getApplication().getRequiredPlugins();
        isEnabled        = mod.isEnabled();
    }

    public static List<Module> getModules() {
        return Mc2Web.getModuleManager().getModules().stream().map(Module::new).collect(Collectors.toList());
    }
}
