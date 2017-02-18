package fr.kwizzy.app.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

import fr.kwizzy.app.Mc2Web;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.yaml.snakeyaml.error.YAMLException;

public class ModuleLoader
{

    private HashMap<String, Module> modules = new HashMap<>();

    private ClassLoader loader;
    private Mc2Web      mc2Web;

    public ModuleLoader(ClassLoader loader, Mc2Web instance)
    {
        this.loader = loader;
        this.mc2Web = instance;
    }

    public Module loadModule(File file) throws InvalidModuleException
    {
        if (!file.exists())
        {
            throw new InvalidModuleException(new FileNotFoundException(file.getPath() + " does not exist"));
        }

        ModuleInformations description = null;
        try
        {
            description = getDescription(file);
            Mc2Web.toConsole(ChatColor.GOLD,  "Loading module: " + description.getName());
        }
        catch (InvalidDescriptionException e)
        {
            throw new InvalidModuleException(e);
        }

        try
        {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(loader, file.toURI().toURL());
        }
        catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | MalformedURLException x)
        {
            x.printStackTrace();
        }

        try
        {
            Class<?> moduleClass = this.loader.loadClass(description.getMain());
            Constructor<?> moduleConstructor = moduleClass.getConstructor(new Class<?>[]{});
            Module obj = (Module) moduleConstructor.newInstance(new Object[]{});
            obj.setModuleInformations(description);
            obj.setMc2Web(mc2Web);
            modules.put(description.getName(), obj);
            return obj;
        }
        catch (ClassNotFoundException | NoSuchMethodException
                | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            throw new InvalidModuleException(e);
        }
    }

    public void enableModule(Module module)
    {
        for (String s : module.getApplication().getRequiredPlugins()) {
            if(Bukkit.getPluginManager().getPlugin(s) == null)
            {
                disableModule(module, "A plugin in dependency is missing (plugin(s) in dependency: " + Arrays.asList(module.getApplication().getRequiredPlugins())+ ")");
                return;
            }
        }
        Mc2Web.toConsole(ChatColor.GOLD, "Enabling module: " + module.getName());
        module.onEnable();
        module.setEnabled(true);
    }

    public void disableModule(Module module)
    {
        Mc2Web.toConsole(ChatColor.RED, "Disabling module: " + module.getName());
        module.onDisable();
        module.setEnabled(false);
    }

    public void disableModule(Module module, String reason)
    {
        Mc2Web.toConsole(ChatColor.RED,  "Disabling module: " + module.getName());
        Mc2Web.toConsole(ChatColor.RED,  "   Reason: " +reason);
        module.onDisable();
        module.setEnabled(false);
    }

    public ModuleInformations getDescription(File file) throws InvalidDescriptionException
    {
        Validate.notNull(file, "File cannot be null");

        JarFile jar = null;
        InputStream stream = null;

        try
        {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("module.yml");

            if (entry == null)
            {
                throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain module.yml"));
            }

            stream = jar.getInputStream(entry);

            return new ModuleInformations(stream);
        }
        catch (IOException | YAMLException ex)
        {
            throw new InvalidDescriptionException(ex);
        } finally
        {
            if (jar != null)
            {
                try
                {
                    jar.close();
                }
                catch (IOException e)
                {
                }
            }
            if (stream != null)
            {
                try
                {
                    stream.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }

    public HashMap<String, Module> getModules()
    {
        return modules;
    }
}